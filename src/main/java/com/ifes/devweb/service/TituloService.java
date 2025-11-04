package com.ifes.devweb.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ifes.devweb.dto.TituloRequestDTO;
import com.ifes.devweb.execption.RecursoNaoEncontradoExecption;
import com.ifes.devweb.model.Ator;
import com.ifes.devweb.model.Classe;
import com.ifes.devweb.model.Diretor;
import com.ifes.devweb.model.Elenco;
import com.ifes.devweb.model.Titulo;
import com.ifes.devweb.repository.AtorRepository;
import com.ifes.devweb.repository.TituloRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TituloService {
    private final TituloRepository tituloRepository;
    private final AtorRepository atorRepository;
    private final DiretorService diretorService;
    private final ClasseService classeService;

    public void salvarTitulo(TituloRequestDTO dto) {
        Titulo titulo = new Titulo();
        titulo.setNome(dto.nome());
        titulo.setAno(dto.ano());
        titulo.setSinopse(dto.sinopse());
        titulo.setCategoria(dto.categoria());
        Diretor diretor = diretorService.buscarDiretorPorId(dto.idDiretor());
        Classe classe = classeService.buscarClassePorId(dto.idClasse());

        titulo.setDiretor(diretor);
        titulo.setClasse(classe);
        titulo.getElenco().clear();

        if(dto.atores() != null) {
            for (UUID atorId : dto.atores()) {
                Ator ator = atorRepository.findById(atorId)
                        .orElseThrow(() -> new RecursoNaoEncontradoExecption("Ator não encontrado: " + atorId));
                Elenco elenco = new Elenco();
                elenco.setAtor(ator);
                elenco.setTitulo(titulo);
                titulo.getElenco().add(elenco);
            }
        }

        tituloRepository.save(titulo);
    }

    public List<Titulo> listarTitulos() {
        return tituloRepository.findAll();
    }

    public Titulo buscarTituloPorId(UUID id) {
        return tituloRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoExecption("Título não encontrado"));
    }

    public Titulo atualizarTitulo(UUID id, Titulo tituloAtualizado) {
        return tituloRepository.findById(id).map(titulo -> {
            titulo.setNome(tituloAtualizado.getNome());
            titulo.setAno(tituloAtualizado.getAno());
            titulo.setSinopse(tituloAtualizado.getSinopse());
            titulo.setCategoria(tituloAtualizado.getCategoria());
            titulo.setDiretor(tituloAtualizado.getDiretor());
            titulo.setClasse(tituloAtualizado.getClasse());
            return tituloRepository.save(titulo);
        }).orElseThrow(() -> new RecursoNaoEncontradoExecption("Título não encontrado"));
    }

    public void deletarTitulo(UUID id) {
        if (!tituloRepository.existsById(id)) {
            throw new RecursoNaoEncontradoExecption("Título não encontrado");
        }
        tituloRepository.deleteById(id);
    }

}
