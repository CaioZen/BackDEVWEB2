package com.ifes.devweb.controller;

import com.ifes.devweb.model.Classe;
import com.ifes.devweb.service.ClasseService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/classes")
public class   ClasseController {
    private final ClasseService classeService;

    public ClasseController(ClasseService classeService) {
        this.classeService = classeService;
    }

    @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Classe cadastrada com sucesso"),
          @ApiResponse(responseCode = "400", description = "Erro ao cadastrar classe")
    })
    @PostMapping("/inserir")
    public ResponseEntity<Classe> inserirClasse(@RequestBody Classe classe){
        return ResponseEntity.ok(classeService.salvarClasse(classe));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listando classes")
    })
    @GetMapping("/listar")
    public ResponseEntity<List<Classe>> listarClasses(){
        return ResponseEntity.ok(classeService.listarClasses());
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Classe encontrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Classe não encontrada")
    })
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Classe> buscarClassePorId(@PathVariable String id){
        return ResponseEntity.ok(classeService.buscarClassePorId(UUID.fromString(id)));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Classe alterada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Classe não encontrada")
    })
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Classe> atualizarClasse(@PathVariable String id, @RequestBody Classe classeAtualizada){
        return ResponseEntity.ok(classeService.atualizarClasse(UUID.fromString(id), classeAtualizada));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Classe deletada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Classe não encontrada")
    })
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarClasse(@PathVariable String id) {
        classeService.deletarClasse(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }
}
