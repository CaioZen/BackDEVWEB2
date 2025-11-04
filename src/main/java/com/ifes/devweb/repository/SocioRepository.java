package com.ifes.devweb.repository;

import com.ifes.devweb.model.Socio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SocioRepository extends JpaRepository<Socio, UUID> {
}
