package com.ifes.devweb.dto;

import com.ifes.devweb.model.TipoItem;

import java.util.UUID;

public record ItemDTO(
    int numSerie,
    String dtAquisicao,
    TipoItem tipoItem,
    UUID idTitulo
) {
}
