package com.ifes.devweb.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifes.devweb.model.Titulo;

public interface TituloRepository extends JpaRepository<Titulo, UUID> {
    List<Titulo> findTituloByCategoria(String categoria);
}
