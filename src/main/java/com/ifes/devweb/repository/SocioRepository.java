package com.ifes.devweb.repository;

import com.ifes.devweb.model.Socio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface SocioRepository extends JpaRepository<Socio, UUID> {
    @Query("SELECT MAX(s.numInscricao) FROM Socio s")
    Integer findNumInscricaoMax();

    //@Query("SELECT COUNT(s) > 0 FROM Socio s WHERE s.cpf = :cpf")
    boolean existsByCpf(@Param("cpf") String cpf);
}
