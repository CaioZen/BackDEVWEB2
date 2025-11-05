package com.ifes.devweb.service;

import com.ifes.devweb.dto.DependenteRequestDTO;
import com.ifes.devweb.execption.RecursoNaoEncontradoException;
import com.ifes.devweb.model.Dependente;
import com.ifes.devweb.model.Socio;
import com.ifes.devweb.repository.DependenteRepository;
import com.ifes.devweb.repository.SocioRepository;
import com.ifes.devweb.utils.ValidadorData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DependenteService {
    private final DependenteRepository dependenteRepository;
    private final SocioRepository socioRepository;

    public Dependente salvarDependente(DependenteRequestDTO dto) {
        Socio socio = socioRepository.findById(dto.idSocio()).orElseThrow(()-> new RecursoNaoEncontradoException("Socio não encontrado"));
        Dependente dependente = new Dependente();
        if (dependenteRepository.findNumInscricaoMax() == null)
            dependente.setNumInscricao(0);
        else
            dependente.setNumInscricao(dependenteRepository.findNumInscricaoMax()+1);
        dependente.setSocio(socio);
        dependente.setNome(dto.nome());
        dependente.setSexo(dto.sexo());
        dependente.setDtNascimento(ValidadorData.validar(dto.dtNascimento()));
        dependente.setAtivo(true);
        socio.getDependentes().add(dependente);
        return dependenteRepository.save(dependente);
    }

    public List<Dependente> listarDependentes() {
        return dependenteRepository.findAll();
    }

    public List<Dependente> listarDependentesPorIdDoSocio(UUID id){
        return dependenteRepository.findBySocioIdCliente(id);
    }

    public Dependente buscarDependentePorId(UUID id){
        return dependenteRepository.findById(id).orElseThrow(()-> new RecursoNaoEncontradoException("Dependente não encontrado"));
    }

    public Dependente atualizarDependente(UUID id, Dependente dependente){
        return dependenteRepository.findById(id).map(socio -> {
            socio.setNome(dependente.getNome());
            socio.setSexo(dependente.getSexo());
            socio.setDtNascimento(dependente.getDtNascimento());
            return dependenteRepository.save(socio);
        }).orElseThrow(()-> new RecursoNaoEncontradoException("Dependente não encontrado"));
    }

    public Dependente statusDependente(UUID id) {
        return dependenteRepository.findById(id).map(dependente -> {
            if(dependente.isAtivo())
                dependente.setAtivo(false);
            else
                dependente.setAtivo(true);
            return dependenteRepository.save(dependente);

        }).orElseThrow(() -> new RecursoNaoEncontradoException("Dependente não encontrado"));
    }

    public void deletarDependente(UUID id) {
        if(!dependenteRepository.existsById(id)){
            throw new RecursoNaoEncontradoException("Socio não encontrado");
        }
        dependenteRepository.deleteAll(dependenteRepository.findBySocioIdCliente(id));
        dependenteRepository.deleteById(id);
    }
}
