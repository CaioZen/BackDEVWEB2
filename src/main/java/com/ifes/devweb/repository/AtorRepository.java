package com.ifes.devweb.repository;

import com.ifes.devweb.model.Ator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AtorRepository extends JpaRepository<Ator, UUID> {
}
