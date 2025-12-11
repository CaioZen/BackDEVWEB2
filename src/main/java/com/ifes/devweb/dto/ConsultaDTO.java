package com.ifes.devweb.dto;

import java.util.List;

public record ConsultaDTO(
    String nome,
    List<String> atores,
    String diretor,
    int ano,
    String sinopse,
    String categoria,
    String classe,
    float valorLocacao
) {
}
