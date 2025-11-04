package com.ifes.devweb.service;

import com.ifes.devweb.execption.RecursoNaoEncontradoExecption;
import com.ifes.devweb.model.Dependente;
import com.ifes.devweb.model.Socio;
import com.ifes.devweb.repository.DependenteRepository;
import com.ifes.devweb.repository.SocioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SocioService {
    private final SocioRepository socioRepository;
    private final DependenteRepository dependenteRepository;

    public Socio salvarSocio(Socio socio) {
        socio.setAtivo(true);
        return socioRepository.save(socio);
    }

    public List<Socio> listarSocios(){
        return socioRepository.findAll();
    }

    public Socio buscarSocioPorId(UUID id){
        return socioRepository.findById(id).orElseThrow(()-> new RecursoNaoEncontradoExecption("Socio não encontrado"));
    }

    public Socio atualizarSocio(UUID id, Socio socioAtualizado){
        return socioRepository.findById(id).map(socio -> {
            socio.setNome(socioAtualizado.getNome());
            socio.setCpf(socioAtualizado.getCpf());
            socio.setSexo(socioAtualizado.getSexo());
            socio.setDtNascimento(socioAtualizado.getDtNascimento());
            socio.setEndereco(socioAtualizado.getEndereco());
            socio.setTel(socioAtualizado.getTel());
            return  socioRepository.save(socio);
        }).orElseThrow(()-> new RecursoNaoEncontradoExecption("Socio não encontrado"));
    }

    public Socio desativarSocio(UUID id) {
        return socioRepository.findById(id).map(socio -> {
            socio.setAtivo(false);
            for (Dependente dependente : dependenteRepository.findBySocioIdCliente(id)) {
                dependente.setAtivo(false);
                dependenteRepository.save(dependente);
            }
            return socioRepository.save(socio);

        }).orElseThrow(() -> new RecursoNaoEncontradoExecption("Socio não encontrado"));
    }

    public Socio reativarSocio(UUID id) {
        return socioRepository.findById(id).map(socio ->  {
            int count = 0;
            socio.setAtivo(true);
            for (Dependente dependente : dependenteRepository.findBySocioIdCliente(id)) {
                if (count != 3) {
                    dependente.setAtivo(true);
                    dependenteRepository.save(dependente);
                    count++;
                }
            }
            return  socioRepository.save(socio);
        }).orElseThrow(() -> new RecursoNaoEncontradoExecption("Socio não encontrado"));
    }

    public void deletarSocio(UUID id) {
        if(!socioRepository.existsById(id)){
            throw new RecursoNaoEncontradoExecption("Socio não encontrado");
        }
        dependenteRepository.deleteAll(dependenteRepository.findBySocioIdCliente(id));
        socioRepository.deleteById(id);
    }
}
