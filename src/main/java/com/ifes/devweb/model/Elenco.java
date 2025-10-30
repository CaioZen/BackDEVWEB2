package com.ifes.devweb.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Elenco {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idElenco;
    @ManyToOne
    @JoinColumn(name = "idAtor")
    private Ator ator;
    @ManyToOne
    @JoinColumn(name = "idTitulo")
    private Titulo titulo;
}
