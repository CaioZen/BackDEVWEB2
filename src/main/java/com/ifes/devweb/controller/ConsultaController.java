package com.ifes.devweb.controller;

import com.ifes.devweb.dto.CategoriaDTO;
import com.ifes.devweb.dto.ConsultaDTO;
import com.ifes.devweb.model.Titulo;
import com.ifes.devweb.service.ConsultaService;
import com.ifes.devweb.service.TituloService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/consultas")
@RequiredArgsConstructor
public class ConsultaController {
    private final ConsultaService consultaService;
    private final TituloService tituloService;

    @GetMapping("/selecionar-titulos/{id}")
    public ResponseEntity<ConsultaDTO> consultarTitulo(@PathVariable String id){
        return ResponseEntity.ok(consultaService.consultarTitulo(UUID.fromString(id)));
    }

    @GetMapping("/listar-titulos-ator/{id}")
    public ResponseEntity<List<Titulo>> listarTitulosPorAtor(@PathVariable String id){
        return ResponseEntity.ok(consultaService.listarTitulosPorAtor(UUID.fromString(id)));
    }

    @GetMapping("/listar-titulo-categoria/{id}")
    public ResponseEntity<List<Titulo>> listarTitulosPorCategoria(@PathVariable String id){
        return ResponseEntity.ok(consultaService.listarTitulosPorCategoria(id));
    }

    @GetMapping("/listar-categorias")
    public ResponseEntity<List<CategoriaDTO>> listarCategorias(){
        List<Titulo> titulos =  tituloService.listarTitulos();
        List<CategoriaDTO> categorias = new ArrayList<>();
        for (Titulo titulo : titulos) {
            CategoriaDTO categoriaDTO = new CategoriaDTO(titulo.getNome());
            categorias.add(categoriaDTO);
        }
        return ResponseEntity.ok(categorias);
    }
}
