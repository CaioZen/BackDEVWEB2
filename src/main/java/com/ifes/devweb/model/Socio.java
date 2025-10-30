package com.ifes.devweb.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Socio extends Cliente{
    private String cpf;
    private String endereco;
    private String tel;

    @OneToMany(mappedBy = "socio",cascade = CascadeType.ALL)
    private List<Dependente> dependentes = new ArrayList<>();
}
