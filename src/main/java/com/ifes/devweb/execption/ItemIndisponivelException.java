package com.ifes.devweb.execption;

import lombok.Getter;

@Getter
public class ItemIndisponivelException extends RuntimeException {
    private final String dtDisponivel;
    public ItemIndisponivelException(String message, String dtDisponivel) {
        super(message);
        this.dtDisponivel = dtDisponivel;
    }
}
