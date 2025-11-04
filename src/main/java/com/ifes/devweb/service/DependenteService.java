package com.ifes.devweb.service;

import com.ifes.devweb.execption.RecursoNaoEncontradoExecption;
import com.ifes.devweb.model.Dependente;
import com.ifes.devweb.repository.DependenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DependenteService {
    private final DependenteRepository dependenteRepository;

    public Dependente salvarDependente(Dependente dependente) {
        dependente.setAtivo(true);
        return dependenteRepository.save(dependente);
    }

    public List<Dependente> listarDependentes() {
        return dependenteRepository.findAll();
    }

    public List<Dependente> listarDependentesPorIdDoSocio(UUID id){
        return dependenteRepository.findBySocioIdCliente(id);
    }

    public Dependente buscarDependentePorId(UUID id){
        return dependenteRepository.findById(id).orElseThrow(()-> new RecursoNaoEncontradoExecption("Dependente não encontrado"));
    }

    public Dependente atualizarDependente(UUID id, Dependente dependente){
        return dependenteRepository.findById(id).map(socio -> {
            socio.setNome(dependente.getNome());
            socio.setSexo(dependente.getSexo());
            socio.setDtNascimento(dependente.getDtNascimento());
            return dependenteRepository.save(socio);
        }).orElseThrow(()-> new RecursoNaoEncontradoExecption("Dependente não encontrado"));
    }

    public Dependente desativarDependente(UUID id) {
        return dependenteRepository.findById(id).map(dependente -> {
            dependente.setAtivo(false);
            return dependenteRepository.save(dependente);

        }).orElseThrow(() -> new RecursoNaoEncontradoExecption("Dependente não encontrado"));
    }

    public Dependente reativarDependente(UUID id) {
        return dependenteRepository.findById(id).map(dependente ->  {
            dependente.setAtivo(true);
            return  dependenteRepository.save(dependente);
        }).orElseThrow(() -> new RecursoNaoEncontradoExecption("Dependente não encontrado"));
    }

    public void deletarDependente(UUID id) {
        if(!dependenteRepository.existsById(id)){
            throw new RecursoNaoEncontradoExecption("Socio não encontrado");
        }
        dependenteRepository.deleteAll(dependenteRepository.findBySocioIdCliente(id));
        dependenteRepository.deleteById(id);
    }
}
