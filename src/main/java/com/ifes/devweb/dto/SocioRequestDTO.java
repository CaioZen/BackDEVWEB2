package com.ifes.devweb.dto;

public record SocioRequestDTO(
        String nome,
        String dtNascimento,
        String sexo,
        String endereco,
        String cpf,
        String tel
) {
}
