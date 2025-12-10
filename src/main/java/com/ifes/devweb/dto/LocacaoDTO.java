package com.ifes.devweb.dto;

import java.util.UUID;

public record LocacaoDTO(
        String dtLocacao,
        String dtDevolucaoPrevista,
        String dtDevolucaoEfetiva,
        float valorCobrado,
        float multaCobrada,
        UUID idItem,
        UUID idCliente
) {
}
