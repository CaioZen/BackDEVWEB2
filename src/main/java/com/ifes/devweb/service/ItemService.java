package com.ifes.devweb.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ifes.devweb.execption.RecursoNaoEncontradoExecption;
import com.ifes.devweb.model.Item;
import com.ifes.devweb.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public Item salvarItem(Item item) {
        return itemRepository.save(item);
    }

    public List<Item> listarItems() {
        return itemRepository.findAll();
    }

    public Item buscarItemPorId(UUID id) {
        return itemRepository.findById(id).orElseThrow(()-> new RecursoNaoEncontradoExecption("Item não encontrado"));
    }

    public Item atualizarItem(UUID id, Item itemAtualizado) {
        return itemRepository.findById(id).map(item -> {
            item.setNumSerie(itemAtualizado.getNumSerie());
            item.setDtAquisicao(itemAtualizado.getDtAquisicao());
            item.setTipoItem(itemAtualizado.getTipoItem());
            return itemRepository.save(item);
        }).orElseThrow(()-> new RecursoNaoEncontradoExecption("Item não encontrado"));
    }

    public void deletarItem(UUID id) {
        if (!itemRepository.existsById(id)) {
            throw new RecursoNaoEncontradoExecption("Item não encontrado");
        }
        itemRepository.deleteById(id);
    }
}
