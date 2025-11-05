package com.ifes.devweb.repository;

import com.ifes.devweb.model.Dependente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface DependenteRepository extends JpaRepository<Dependente, UUID> {
    List<Dependente> findBySocioIdCliente(UUID socioId);

    @Query("SELECT MAX(d.numInscricao) FROM Dependente d")
    Integer findNumInscricaoMax();
}
