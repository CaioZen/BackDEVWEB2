package com.ifes.devweb.service;

import com.ifes.devweb.dto.LocacaoDTO;
import com.ifes.devweb.execption.ClienteEmDebitoException;
import com.ifes.devweb.execption.ItemIndisponivelException;
import com.ifes.devweb.execption.RecursoNaoEncontradoException;
import com.ifes.devweb.model.Cliente;
import com.ifes.devweb.model.Item;
import com.ifes.devweb.model.Locacao;
import com.ifes.devweb.repository.ClienteRepository;
import com.ifes.devweb.repository.LocacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LocacaoService {
    private final LocacaoRepository locacaoRepository;
    private final ItemService itemService;
    private final ClienteRepository clienteRepository;

    public Locacao salvarLocacao(LocacaoDTO dto) {
        Locacao locacao = new Locacao();

        List<Locacao> todas = locacaoRepository.findByClienteIdCliente(dto.idCliente());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate hoje = LocalDate.now();

        List<Locacao> debitos = todas.stream()
                        .filter(loc ->{
                            try{
                                LocalDate dtPrevista = LocalDate.parse(loc.getDtDevolucaoPrevista(), dtf);
                                return dtPrevista.isBefore(hoje);
                            }catch(Exception e){
                                return false;
                            }
                        }).toList();

        if (!debitos.isEmpty()) {
            throw new ClienteEmDebitoException(
                    "Cliente possui locações em débito. Iserção bloqueada.",
                    debitos
            );
        }

        List<Locacao> locacoesDoItem = locacaoRepository.findByItemIdItem(dto.idItem());

        boolean itemOcupado = locacoesDoItem.stream().anyMatch(loc -> 
            loc.getDtDevolucaoEfetiva() == null &&
            !LocalDate.parse(loc.getDtDevolucaoPrevista(), dtf).isBefore(hoje)
        );

        if (itemOcupado) {
            throw new ItemIndisponivelException(
                "Item já está locado e ainda não foi devolvido",
                hoje.toString()
            );
        }

        locacao.setDtDevolucaoPrevista(dto.dtDevolucaoPrevista());
        locacao.setValorCobrado(dto.valorCobrado());
        Cliente cliente = clienteRepository.findById(dto.idCliente()).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        locacao.setCliente(cliente);
        Item item = itemService.buscarItemPorId(dto.idItem());
        locacao.setItem(item);
        return locacaoRepository.save(locacao);
    }

    public List<Locacao> listarLocacoes() {
        return locacaoRepository.findAll();
    }

    public Locacao buscarLocacaoPorId(UUID id) {
        return locacaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Locação não encontrada"));
    }

    public Locacao atualizarLocacao(UUID id, LocacaoDTO dto) {
    Locacao locacao = locacaoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Locação não encontrada"));

    if (dto.dtDevolucaoPrevista() != null)
        locacao.setDtDevolucaoPrevista(dto.dtDevolucaoPrevista());

    if (dto.valorCobrado() != 0)
        locacao.setValorCobrado(dto.valorCobrado());

    if (dto.idCliente() != null) {
        Cliente cliente = clienteRepository.findById(dto.idCliente())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        locacao.setCliente(cliente);
    }

    if (dto.idItem() != null) {
        Item item = itemService.buscarItemPorId(dto.idItem());
        locacao.setItem(item);
    }

    return locacaoRepository.save(locacao);
}



    public void deletarLocacao(UUID id) {
        if(!locacaoRepository.existsById(id))
            throw new RecursoNaoEncontradoException("Locação não encontrada");
        Locacao locacao = locacaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Locação não encontrada"));
        if(locacao.getDtDevolucaoEfetiva() ==null)
            locacaoRepository.deleteById(id);
        else
            throw new ItemIndisponivelException("Item indisponível", locacao.getDtDevolucaoPrevista());
    }
}
