package com.ifes.devweb.controller;

import com.ifes.devweb.model.Diretor;
import com.ifes.devweb.service.DiretorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/diretores")
public class DiretorController {

    private final DiretorService diretorService;

    public DiretorController(DiretorService diretorService) {
        this.diretorService = diretorService;
    }

    @PostMapping("/inserir")
    public ResponseEntity<Diretor> inserirDiretor(@RequestBody Diretor diretor){
        return ResponseEntity.ok(diretorService.salvarDiretor(diretor));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Diretor>> listarDiretores(){
        return ResponseEntity.ok(diretorService.listarDiretores());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Diretor> buscarDiretorPorId(@PathVariable String id){
        return ResponseEntity.ok(diretorService.buscarDiretorPorId(UUID.fromString(id)));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Diretor> atualizarDiretor(@PathVariable String id, @RequestBody Diretor diretorAtualizado){
        return ResponseEntity.ok(diretorService.atualizarDiretor(UUID.fromString(id), diretorAtualizado));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarDiretor(@PathVariable String id) {
        diretorService.deletarDiretor(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }
}
