package com.ifes.devweb.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public  abstract class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idCliente;
    @Column(unique = true)
    private int numInscricao;
    private String nome;
    private LocalDate dtNascimento;
    private String sexo;
    private boolean isAtivo;
}
