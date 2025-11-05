package com.ifes.devweb.dto;

import java.util.UUID;

public record DependenteRequestDTO(
        String nome,
        String sexo,
        String dtNascimento,
        UUID idSocio
) {
}
