package com.ifes.devweb.dto;


import java.util.List;
import java.util.UUID;

public record TituloDTO(
    String nome,
    int ano,
    String sinopse,
    String categoria,
    UUID idDiretor,
    UUID idClasse,
    List<UUID> atores
    ) {
} 
