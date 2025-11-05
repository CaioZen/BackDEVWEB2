package com.ifes.devweb.service;


import com.ifes.devweb.execption.RecursoNaoEncontradoException;
import com.ifes.devweb.model.Diretor;
import com.ifes.devweb.repository.DiretorRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DiretorService {
    private final DiretorRepository diretorRepository;

    public Diretor salvarDiretor(Diretor diretor) {
        return diretorRepository.save(diretor);
    }

    public List<Diretor> listarDiretores() {
        return diretorRepository.findAll();
    }

    public Diretor buscarDiretorPorId(UUID id) {
        return diretorRepository.findById(id).orElseThrow(()-> new RecursoNaoEncontradoException("Diretor não encontrado"));
    }

    public Diretor atualizarDiretor(UUID id, Diretor diretorAtualizado) {
        return diretorRepository.findById(id).map(diretor -> {
            diretor.setNome(diretorAtualizado.getNome());
            return diretorRepository.save(diretor);
        }).orElseThrow(()-> new RecursoNaoEncontradoException("Diretor não encontrado"));
    }

    public void deletarDiretor(UUID id) {
        if (!diretorRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Diretor não encontrado");
        }
        diretorRepository.deleteById(id);
    }
}
