package com.ifes.devweb.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Titulo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idTitulo;
    private String nome;
    private int ano;
    private String sinopse;
    private String categoria;
    @OneToMany(mappedBy = "titulo", cascade = CascadeType.ALL)
    private List<Elenco> elenco = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "idDiretor")
    private Diretor diretor;
    @ManyToOne
    @JoinColumn(name = "idClasse")
    private Classe classe;
}
