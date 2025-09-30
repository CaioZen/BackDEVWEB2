package com.ifes.devweb.controller;

import com.ifes.devweb.model.Classe;
import com.ifes.devweb.service.ClasseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/classes")
public class ClasseController {
    private final ClasseService classeService;

    public ClasseController(ClasseService classeService) {
        this.classeService = classeService;
    }

    @PostMapping("/inserir")
    public ResponseEntity<Classe> inserirClasse(@RequestBody Classe classe){
        return ResponseEntity.ok(classeService.salvarClasse(classe));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Classe>> listarClasses(){
        return ResponseEntity.ok(classeService.listarClasses());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Classe> buscarClassePorId(@PathVariable String id){
        return ResponseEntity.ok(classeService.buscarClassePorId(UUID.fromString(id)));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Classe> atualizarClasse(@PathVariable String id, @RequestBody Classe classeAtualizada){
        return ResponseEntity.ok(classeService.atualizarClasse(UUID.fromString(id), classeAtualizada));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarClasse(@PathVariable String id) {
        classeService.deletarClasse(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }
}
