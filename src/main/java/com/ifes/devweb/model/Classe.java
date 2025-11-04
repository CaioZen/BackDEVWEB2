package com.ifes.devweb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Classe {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idClasse;
    private String nome;
    private float valor;
    private int dataDevolucao;
}
