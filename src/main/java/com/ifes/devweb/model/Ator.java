package com.ifes.devweb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import java.util.UUID;

@Entity
@Data
public class Ator {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idAtor;
    private String nome;
}
