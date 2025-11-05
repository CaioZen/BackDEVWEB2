package com.ifes.devweb.controller;

import com.ifes.devweb.dto.DependenteRequestDTO;
import com.ifes.devweb.model.Dependente;
import com.ifes.devweb.service.DependenteService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/dependentes")
@RequiredArgsConstructor
public class DependenteController {
    private final DependenteService dependenteService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dependente cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao cadastrar o dependente")
    })
    @PostMapping("/inserir")
    public ResponseEntity<Dependente> inserirDependente(@RequestBody DependenteRequestDTO dto){
        return ResponseEntity.ok(dependenteService.salvarDependente(dto));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listando dependentes")
    })
    @GetMapping("/listar")
    public ResponseEntity<List<Dependente>> listarDependentes(){
        return ResponseEntity.ok(dependenteService.listarDependentes());
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listando dependentes por id do socio")
    })
    @GetMapping("/listar/{id}")
    public ResponseEntity<List<Dependente>> listarDependentesPorIdDoSocio(@PathVariable String id){
        return ResponseEntity.ok(dependenteService.listarDependentesPorIdDoSocio(UUID.fromString(id)));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dependente encontrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dependente não encontrado")
    })
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Dependente> buscarDependentePorId(@PathVariable String id){
        return ResponseEntity.ok(dependenteService.buscarDependentePorId(UUID.fromString(id)));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dependente alterado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dependente não encontrado")
    })
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Dependente> atualizarDependente(@PathVariable String id, @RequestBody Dependente dependenteAtualizado){
        return ResponseEntity.ok(dependenteService.atualizarDependente(UUID.fromString(id), dependenteAtualizado));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dependente excluído com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dependente não encontrado")
    })
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarDependente(@PathVariable String id) {
        dependenteService.deletarDependente(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dependente desativado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dependente não encontrado")
    })
    @PutMapping("/desativar/{id}")
    public ResponseEntity<Dependente> desativarDependentePorId(@PathVariable String id){
        return ResponseEntity.ok(dependenteService.desativarDependente(UUID.fromString(id)));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dependente reativado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dependente não encontrado")
    })
    @PutMapping("/reativar/{id}")
    public ResponseEntity<Dependente> reativarDependentePorId(@PathVariable String id){
        return ResponseEntity.ok(dependenteService.reativarDependente(UUID.fromString(id)));
    }
}
