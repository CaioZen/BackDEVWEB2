package com.ifes.devweb.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifes.devweb.dto.TituloRequestDTO;
import com.ifes.devweb.model.Titulo;
import com.ifes.devweb.service.TituloService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/titulos")
public class TituloController {
    private final TituloService tituloService;

    public TituloController(TituloService tituloService) {
        this.tituloService = tituloService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Título cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao cadastrar o título")
    })
    @PostMapping("/inserir")
    public ResponseEntity<TituloRequestDTO> inserirTitulo(@RequestBody TituloRequestDTO titulo){
        tituloService.salvarTitulo(titulo);
        return ResponseEntity.ok(titulo);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listando títulos")
    })
    @GetMapping("/listar")
    public ResponseEntity<List<Titulo>> listarTitulos(){
        return ResponseEntity.ok(tituloService.listarTitulos());
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Título encontrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Título não encontrado")
    })
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Titulo> buscarTituloPorId(@PathVariable String id){
        return ResponseEntity.ok(tituloService.buscarTituloPorId(UUID.fromString(id)));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Título alterado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Título não encontrado")
    })
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Titulo> atualizarTitulo(@PathVariable String id, @RequestBody Titulo titulo) {
        return ResponseEntity.ok(tituloService.atualizarTitulo(UUID.fromString(id), titulo));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Título excluído com sucesso"),
            @ApiResponse(responseCode = "400", description = "Título não encontrado")
    })
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarTitulo(@PathVariable String id){
        tituloService.deletarTitulo(UUID.fromString(id));
        return ResponseEntity.ok().build();
    }
}
