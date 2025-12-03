package com.ifes.devweb.execption;

import com.ifes.devweb.model.Locacao;
import lombok.Getter;

import java.util.List;

@Getter
public class ClienteEmDebitoException extends RuntimeException {
    private final List<Locacao> locacaoList;

    public ClienteEmDebitoException(String message, List<Locacao> locacaoList) {
        super(message);
        this.locacaoList = locacaoList;
    }


}
