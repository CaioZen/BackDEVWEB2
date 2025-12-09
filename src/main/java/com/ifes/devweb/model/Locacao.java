package com.ifes.devweb.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        this.dtLocacao = LocalDate.now().toString();
        if (this.dtDevolucaoPrevista == null)
            this.dtDevolucaoPrevista = calcularDevolucao(LocalDate.now());
        //this.dtDevolucaoEfetiva = null;
        if (this.valorCobrado == 0)
            this.valorCobrado = item.getTitulo().getClasse().getValor();
        this.multaCobrada = 0;
    }

    @PreUpdate
    protected void onUpdate(){

    }

    protected String calcularDevolucao(LocalDate data){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate novaData = data.plusDays(item.getTitulo().getClasse().getDataDevolucao());
        return novaData.format(dtf);
    }
}
