package com.ifes.devweb.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
public class Locacao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String dtLocacao;
    private String dtDevolucaoPrevista;
    private String dtDevolucaoEfetiva;
    private float valorCobrado;
    private float multaCobrada;

    @ManyToOne
    @JoinColumn(name = "idItem")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    @PrePersist
    protected void onCreate() {
        this.dtDevolucaoPrevista = LocalDate.now().toString();
    }
}
