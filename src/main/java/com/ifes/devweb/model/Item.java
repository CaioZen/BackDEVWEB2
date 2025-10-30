package com.ifes.devweb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idItem;
    private int numSerie;
    private String dtAquisicao;
    private TipoItem tipoItem;
}
