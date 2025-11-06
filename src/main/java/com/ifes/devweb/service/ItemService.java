package com.ifes.devweb.service;

import java.util.List;
import java.util.UUID;

import com.ifes.devweb.dto.ItemDTO;
import com.ifes.devweb.model.Titulo;
import com.ifes.devweb.repository.TituloRepository;
import org.springframework.stereotype.Service;

import com.ifes.devweb.execption.RecursoNaoEncontradoException;
import com.ifes.devweb.model.Item;
import com.ifes.devweb.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final TituloService tituloService;

    public Item salvarItem(ItemDTO dto) {
        Item item = new Item();
        item.setDtAquisicao(dto.dtAquisicao());
        item.setTipoItem(dto.tipoItem());
        item.setNumSerie(dto.numSerie());
        Titulo titulo = tituloService.buscarTituloPorId(dto.idTitulo());
        item.setTitulo(titulo);
        return itemRepository.save(item);
    }

    public List<Item> listarItems() {
        return itemRepository.findAll();
    }

    public Item buscarItemPorId(UUID id) {
        return itemRepository.findById(id).orElseThrow(()-> new RecursoNaoEncontradoException("Item não encontrado"));
    }

    public Item atualizarItem(UUID id, Item itemAtualizado) {
        return itemRepository.findById(id).map(item -> {
            item.setNumSerie(itemAtualizado.getNumSerie());
            item.setDtAquisicao(itemAtualizado.getDtAquisicao());
            item.setTipoItem(itemAtualizado.getTipoItem());
            return itemRepository.save(item);
        }).orElseThrow(()-> new RecursoNaoEncontradoException("Item não encontrado"));
    }

    public void deletarItem(UUID id) {
        if (!itemRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Item não encontrado");
        }
        itemRepository.deleteById(id);
    }
}
