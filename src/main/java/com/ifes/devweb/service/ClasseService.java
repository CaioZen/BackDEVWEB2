package com.ifes.devweb.service;

import com.ifes.devweb.execption.RecursoNaoEncontradoExecption;
import com.ifes.devweb.model.Classe;
import com.ifes.devweb.repository.ClasseRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClasseService {
    private final ClasseRepository classeRepository;

    public Classe salvarClasse(Classe classe) {
        return classeRepository.save(classe);
    }

    public List<Classe> listarClasses() {
        return classeRepository.findAll();
    }

    public Classe buscarClassePorId(UUID id) {
        return classeRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoExecption("Classe não encontrada"));
    }

    public Classe atualizarClasse(UUID id, Classe classeAtualizada) {
        return classeRepository.findById(id).map(classe -> {
            classe.setNome(classeAtualizada.getNome());
            classe.setValor(classeAtualizada.getValor());
            classe.setDataDevolucao(classeAtualizada.getDataDevolucao());
            return classeRepository.save(classe);
        }).orElseThrow(() -> new RecursoNaoEncontradoExecption("Classe não encontrada"));
    }

    public void deletarClasse(UUID id) {
        if (!classeRepository.existsById(id)) {
            throw new RecursoNaoEncontradoExecption("Classe não encontrada");
        }
        classeRepository.deleteById(id);
    }
}
