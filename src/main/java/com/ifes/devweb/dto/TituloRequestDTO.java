package com.ifes.devweb.dto;

import com.ifes.devweb.model.Classe;
import com.ifes.devweb.model.Diretor;

import java.util.List;
import java.util.UUID;

public record TituloRequestDTO(
    String nome,
    int ano,
    String sinopse,
    String categoria,
    Diretor diretor,
    Classe classe,
    List<UUID> atores
    ) {
} 
