package com.ifes.devweb.repository;

import com.ifes.devweb.model.Locacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public  interface LocacaoRepository extends JpaRepository<Locacao, UUID> {
    List<Locacao> findByClienteId(UUID clienteId);
}
