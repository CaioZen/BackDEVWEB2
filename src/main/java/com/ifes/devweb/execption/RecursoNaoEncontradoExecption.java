package com.ifes.devweb.execption;

public class RecursoNaoEncontradoExecption extends RuntimeException {
    public RecursoNaoEncontradoExecption(String mensagem) {
        super(mensagem);
    }
}
