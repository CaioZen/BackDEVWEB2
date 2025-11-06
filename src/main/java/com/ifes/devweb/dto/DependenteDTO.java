package com.ifes.devweb.dto;

import java.util.UUID;

public record DependenteDTO(
        String nome,
        String sexo,
        String dtNascimento,
        UUID idSocio
) {
}
