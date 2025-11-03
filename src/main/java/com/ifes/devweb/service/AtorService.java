package com.ifes.devweb.service;

import com.ifes.devweb.model.Ator;
import com.ifes.devweb.repository.AtorRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AtorService {
    private final AtorRepository atorRepository;

    public Ator salvarAtor(Ator ator) {
        return atorRepository.save(ator);
    }

    public List<Ator> listarAtores() {
        return atorRepository.findAll();
    }

    public Ator buscarAtorPorId(UUID id) {
        return atorRepository.findById(id).orElseThrow(()-> new RuntimeException("Ator não encontrado"));
    }

    public Ator atualizarAtor(UUID id, Ator atorAtualizado) {
        return atorRepository.findById(id).map(ator -> {
            ator.setNome(atorAtualizado.getNome());
            return atorRepository.save(ator);
        }).orElseThrow(()-> new RuntimeException("Ator não encontrado"));
    }

    public void deletarAtor(UUID id) {
        if (!atorRepository.existsById(id)) {
            throw new RuntimeException("Ator não encontrado");
        }
        atorRepository.deleteById(id);
    }
}
