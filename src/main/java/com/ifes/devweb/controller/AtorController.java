package com.ifes.devweb.controller;

import com.ifes.devweb.model.Ator;
import com.ifes.devweb.service.AtorService;
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

    @PostMapping("/inserir")
    public ResponseEntity<Ator> inserirAtor(@RequestBody Ator ator){
        return ResponseEntity.ok(atorService.salvarAtor(ator));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Ator>> listarAtores(){
        return ResponseEntity.ok(atorService.listarAtores());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Ator> buscarAtorPorId(@PathVariable String id){
        return ResponseEntity.ok(atorService.buscarAtorPorId(UUID.fromString(id)));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Ator> atualizarAtor(@PathVariable String id, @RequestBody Ator atorAtualizado){
        return ResponseEntity.ok(atorService.atualizarAtor(UUID.fromString(id), atorAtualizado));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarAtor(@PathVariable String id) {
        atorService.deletarAtor(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }
}
