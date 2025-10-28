package com.ifes.devweb.controller;

import com.ifes.devweb.model.Diretor;
import com.ifes.devweb.service.DiretorService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Diretor cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao cadastrar o diretor")
    })
    @PostMapping("/inserir")
    public ResponseEntity<Diretor> inserirDiretor(@RequestBody Diretor diretor){
        return ResponseEntity.ok(diretorService.salvarDiretor(diretor));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listando diretores")
    })
    @GetMapping("/listar")
    public ResponseEntity<List<Diretor>> listarDiretores(){
        return ResponseEntity.ok(diretorService.listarDiretores());
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Diretor encontrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Diretor não encontrado")
    })
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Diretor> buscarDiretorPorId(@PathVariable String id){
        return ResponseEntity.ok(diretorService.buscarDiretorPorId(UUID.fromString(id)));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Diretor alterado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Diretor não encontrado")
    })
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Diretor> atualizarDiretor(@PathVariable String id, @RequestBody Diretor diretorAtualizado){
        return ResponseEntity.ok(diretorService.atualizarDiretor(UUID.fromString(id), diretorAtualizado));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Diretor excluido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Diretor não encontrado")
    })
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarDiretor(@PathVariable String id) {
        diretorService.deletarDiretor(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }
}
