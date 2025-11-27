package com.ifes.devweb.service;

import com.ifes.devweb.dto.LocacaoDTO;
import com.ifes.devweb.execption.RecursoNaoEncontradoException;
import com.ifes.devweb.model.Cliente;
import com.ifes.devweb.model.Item;
import com.ifes.devweb.model.Locacao;
import com.ifes.devweb.repository.ClienteRepository;
import com.ifes.devweb.repository.LocacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LocacaoService {
    private final LocacaoRepository locacaoRepository;
    private final ItemService itemService;
    private final ClienteRepository clienteRepository;

    public void salvarLocacao(LocacaoDTO dto) {
        Locacao locacao = new Locacao();
        locacao.setDtDevolucaoPrevista(dto.dtDevolucaoPrevista());
        locacao.setValorCobrado(dto.valorCobrado());
        Item item = itemService.buscarItemPorId(dto.idItem());
        Cliente cliente = clienteRepository.findById(dto.idCliente()).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        locacao.setCliente(cliente);
        locacao.setItem(item);
        locacaoRepository.save(locacao);
    }

    public List<Locacao> listarLocacoes() {
        return locacaoRepository.findAll();
    }

    public Locacao buscarLocacaoPorId(UUID id) {
        return locacaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Locação não encontrada"));
    }

    public Locacao atualizarLocacao(UUID id, Locacao locacaoAtualizada) {
        return  locacaoRepository.findById(id).map(locacao -> {
            locacao.setDtLocacao(locacaoAtualizada.getDtLocacao());
            locacao.setValorCobrado(locacaoAtualizada.getValorCobrado());
            locacao.setCliente(locacaoAtualizada.getCliente());
            locacao.setItem(locacaoAtualizada.getItem());
            locacao.setDtDevolucaoPrevista(locacaoAtualizada.getDtDevolucaoPrevista());
            locacao.setDtDevolucaoEfetiva(locacaoAtualizada.getDtDevolucaoEfetiva());
            locacao.setMultaCobrada(locacaoAtualizada.getMultaCobrada());
            return locacaoRepository.save(locacao);
        }).orElseThrow(() -> new RuntimeException("Locação não encontrada"));
    }

    public void deletarLocacao(UUID id) {
        if(!locacaoRepository.existsById(id))
            throw new RecursoNaoEncontradoException("Locação não encontrada");
        locacaoRepository.deleteById(id);
    }
}
