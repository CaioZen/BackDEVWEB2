package com.ifes.devweb.service;

import com.ifes.devweb.dto.ConsultaDTO;
import com.ifes.devweb.execption.RecursoNaoEncontradoException;
import com.ifes.devweb.model.Elenco;
import com.ifes.devweb.model.Titulo;
import com.ifes.devweb.repository.TituloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConsultaService {
    private final TituloRepository tituloRepository;

    public ConsultaDTO consultarTitulo(UUID id){
        Titulo titulo = tituloRepository.findById(id).orElseThrow(()-> new RecursoNaoEncontradoException("Título não encontrado"));
        List<String> nomesAtores = titulo.getElenco()
                .stream()
                .map(elenco -> elenco.getAtor().getNome())
                .toList();

        return new ConsultaDTO(
                titulo.getNome(),
                nomesAtores,
                titulo.getDiretor().getNome(),
                titulo.getAno(),
                titulo.getSinopse(),
                titulo.getCategoria(),
                titulo.getClasse().getNome(),
                titulo.getClasse().getValor()
        );
    }
}
