package com.ifes.devweb.repository;

import com.ifes.devweb.model.Classe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClasseRepository extends JpaRepository<Classe, UUID> {
}
