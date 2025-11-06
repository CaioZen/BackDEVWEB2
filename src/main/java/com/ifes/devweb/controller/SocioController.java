package com.ifes.devweb.controller;

import com.ifes.devweb.dto.SocioDTO;
import com.ifes.devweb.model.Socio;
import com.ifes.devweb.service.SocioService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/socios")
@RequiredArgsConstructor
public class SocioController {
    private final SocioService socioService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Socio cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao cadastrar o socio")
    })
    @PostMapping("/inserir")
    public ResponseEntity<Socio> inserirSocio(@RequestBody SocioDTO dto){
        return ResponseEntity.ok(socioService.salvarSocio(dto));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listando socios")
    })
    @GetMapping("/listar")
    public ResponseEntity<List<Socio>> listarSocios(){
        return ResponseEntity.ok(socioService.listarSocios());
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Socio encontrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Socio não encontrado")
    })
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Socio> buscarSocioPorId(@PathVariable String id){
        return ResponseEntity.ok(socioService.buscarSocioPorId(UUID.fromString(id)));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Socio alterado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Socio não encontrado")
    })
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Socio> atualizarSocio(@PathVariable String id, @RequestBody Socio socioAtualizado){
        return ResponseEntity.ok(socioService.atualizarSocio(UUID.fromString(id), socioAtualizado));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Socio excluido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Socio não encontrado")
    })
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarSocio(@PathVariable String id) {
        socioService.deletarSocio(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Socio desativado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Socio não encontrado")
    })
    @PutMapping("/desativar/{id}")
    public ResponseEntity<Socio> desativarSocioPorId(@PathVariable String id){
        return ResponseEntity.ok(socioService.desativarSocio(UUID.fromString(id)));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Socio reativado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Socio não encontrado")
    })
    @PutMapping("/reativar/{id}")
    public ResponseEntity<Socio> reativarSocioPorId(@PathVariable String id){
        return ResponseEntity.ok(socioService.reativarSocio(UUID.fromString(id)));
    }
}
