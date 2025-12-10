package com.ifes.devweb.dto;

import java.util.UUID;

public record LocacaoDTO(
        String dtLocacao,
        String dtDevolucaoPrevista,
        String dtDevolucaoEfetiva,
        Float valorCobrado,
        Float multaCobrada,
        UUID idItem,
        UUID idCliente
) {
}
