package com.ifes.devweb.repository;

import com.ifes.devweb.model.Elenco;
import com.ifes.devweb.model.Titulo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.UUID;

public interface ElencoRepository extends Repository<Elenco, UUID> {
    @Query("SELECT e.titulo FROM Elenco e WHERE e.ator.idAtor = :idAtor")
    List<Titulo> buscarTitulosPorAtor(UUID idAtor);
}
