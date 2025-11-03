package com.ifes.devweb.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifes.devweb.model.Item;

public interface ItemRepository extends JpaRepository<Item, UUID> {    
}
