package com.ifes.devweb.controller;

import java.util.List;
import java.util.UUID;

import com.ifes.devweb.dto.ItemDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ifes.devweb.model.Item;
import com.ifes.devweb.service.ItemService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/api/items")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao cadastrar o item")
    })
    @PostMapping("/inserir")
    public ResponseEntity<Item> inserirItem(@RequestBody ItemDTO dto){
        return ResponseEntity.ok(itemService.salvarItem(dto));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listando itens")
    })
    @GetMapping("/listar")
    public ResponseEntity<List<Item>> listarItems(){
        return ResponseEntity.ok(itemService.listarItems());
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item encontrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Item não encontrado")
    })
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Item> buscarItemPorId(@PathVariable String id){
        return ResponseEntity.ok(itemService.buscarItemPorId(UUID.fromString(id)));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item alterado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Item não encontrado")
    })
    @PutMapping("atualizar/{id}")
    public ResponseEntity<Item> atualizarItem(@PathVariable String id, @RequestBody Item item) {
        return ResponseEntity.ok(itemService.atualizarItem(UUID.fromString(id), item));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item excluido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Item não encontrado")
    })
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarItem(@PathVariable String id){
        itemService.deletarItem(UUID.fromString(id));
        return ResponseEntity.ok().build();
    }
}
