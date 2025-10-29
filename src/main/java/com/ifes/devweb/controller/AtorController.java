package com.ifes.devweb.controller;

import com.ifes.devweb.model.Ator;
import com.ifes.devweb.service.AtorService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/atores")
public class AtorController{
    private final AtorService atorService;

    public AtorController(AtorService atorService) {
        this.atorService = atorService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ator cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao cadastrar ator")
    })
    @PostMapping("/inserir")
    public ResponseEntity<Ator> inserirAtor(@RequestBody Ator ator){
        return ResponseEntity.ok(atorService.salvarAtor(ator));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listando atores"),
    })
    @GetMapping("/listar")
    public ResponseEntity<List<Ator>> listarAtores(){
        return ResponseEntity.ok(atorService.listarAtores());
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ator encontrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Ator não encontrado")
    })
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Ator> buscarAtorPorId(@PathVariable String id){
        return ResponseEntity.ok(atorService.buscarAtorPorId(UUID.fromString(id)));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ator alterado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Ator não encontrado")
    })
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Ator> atualizarAtor(@PathVariable String id, @RequestBody Ator atorAtualizado){
        return ResponseEntity.ok(atorService.atualizarAtor(UUID.fromString(id), atorAtualizado));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ator excluido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Ator não encontrado")
    })
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarAtor(@PathVariable String id) {
        atorService.deletarAtor(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }
}
