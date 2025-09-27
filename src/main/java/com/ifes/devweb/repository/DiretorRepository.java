package com.ifes.devweb.repository;

import com.ifes.devweb.model.Diretor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DiretorRepository extends JpaRepository<Diretor, UUID> {
}
