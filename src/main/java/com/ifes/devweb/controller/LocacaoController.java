package com.ifes.devweb.controller;

import com.ifes.devweb.dto.LocacaoDTO;
import com.ifes.devweb.model.Locacao;
import com.ifes.devweb.service.LocacaoService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/locacoes")
public class LocacaoController {
    private final LocacaoService locacaoService;

    public LocacaoController(LocacaoService locacaoService) {
        this.locacaoService = locacaoService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Locação cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao cadastrar a locação")
    })
    @PostMapping("/inserir")
    public ResponseEntity<Locacao> inserirLocacao(@RequestBody LocacaoDTO dto) {
        return ResponseEntity.ok(locacaoService.salvarLocacao(dto));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listando locações")
    })
    @GetMapping("/listar")
    public ResponseEntity<List<Locacao>> listarLocacoes(){
        return ResponseEntity.ok(locacaoService.listarLocacoes());
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Locação encontrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Locação não encontrada")
    })
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Locacao> buscarLocacaoPorId(@PathVariable String id){
        return ResponseEntity.ok(locacaoService.buscarLocacaoPorId(UUID.fromString(id)));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Locação alterada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Locação não encontrada")
    })
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Locacao> atualizarLocacao(@PathVariable String id, @RequestBody LocacaoDTO locacao){
        return ResponseEntity.ok(locacaoService.atualizarLocacao(UUID.fromString(id), locacao));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Locação excluida com sucesso"),
            @ApiResponse(responseCode = "400", description = "Locação não encontrada")
    })
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarItem(@PathVariable String id){
        locacaoService.deletarLocacao(UUID.fromString(id));
        return ResponseEntity.ok().build();
    }
}
