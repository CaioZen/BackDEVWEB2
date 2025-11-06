package com.ifes.devweb.service;

import com.ifes.devweb.dto.SocioDTO;
import com.ifes.devweb.execption.CpfInvalidoException;
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
public class SocioService {
    private final SocioRepository socioRepository;
    private final DependenteRepository dependenteRepository;

    public Socio salvarSocio(SocioDTO dto) {
        if (socioRepository.existsByCpf(dto.cpf()))
            throw new CpfInvalidoException("Cpf ja cadastrado no banco");
        else {
            Socio socio = new Socio();
            socio.setNome(dto.nome());
            socio.setSexo(dto.sexo());
            socio.setEndereco(dto.endereco());
            socio.setTel(dto.tel());
            socio.setCpf(dto.cpf());
            socio.setDtNascimento(ValidadorData.validar(dto.dtNascimento()));
            if (socioRepository.findNumInscricaoMax() == null)
                socio.setNumInscricao(0);
            else
                socio.setNumInscricao(socioRepository.findNumInscricaoMax() + 1);
            socio.setAtivo(true);
            return socioRepository.save(socio);
        }
    }

    public List<Socio> listarSocios(){
        return socioRepository.findAll();
    }

    public Socio buscarSocioPorId(UUID id){
        return socioRepository.findById(id).orElseThrow(()-> new RecursoNaoEncontradoException("Socio não encontrado"));
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
        }).orElseThrow(()-> new RecursoNaoEncontradoException("Socio não encontrado"));
    }

    public Socio desativarSocio(UUID id) {
        return socioRepository.findById(id).map(socio -> {
            socio.setAtivo(false);
            for (Dependente dependente : dependenteRepository.findBySocioIdCliente(id)) {
                dependente.setAtivo(false);
                dependenteRepository.save(dependente);
            }
            return socioRepository.save(socio);

        }).orElseThrow(() -> new RecursoNaoEncontradoException("Socio não encontrado"));
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
        }).orElseThrow(() -> new RecursoNaoEncontradoException("Socio não encontrado"));
    }

    public void deletarSocio(UUID id) {
        if(!socioRepository.existsById(id)){
            throw new RecursoNaoEncontradoException("Socio não encontrado");
        }
        dependenteRepository.deleteAll(dependenteRepository.findBySocioIdCliente(id));
        socioRepository.deleteById(id);
    }
}
