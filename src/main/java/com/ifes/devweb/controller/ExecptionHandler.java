package com.ifes.devweb.controller;

import com.ifes.devweb.execption.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExecptionHandler {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleRecursoNaoEncontrado(RecursoNaoEncontradoException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LimiteDependentesAtivoException.class)
    public ResponseEntity<Map<String, Object>> handleLimiteDependentesAtivos(LimiteDependentesAtivoException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Limite de dependentes ativos excedido");
        body.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(CpfInvalidoException.class)
    public ResponseEntity<Map<String, String>> handleCpfInvalido(CpfInvalidoException ex) {
        Map<String, String> body = new HashMap<>();
        body.put("erro", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(ClienteEmDebitoException.class)
    public ResponseEntity<Map<String, Object>> handleClienteEmDebito(ClienteEmDebitoException ex) {

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Cliente possui locações em débito");
        body.put("message", ex.getMessage());
        body.put("locacoesEmDebito", ex.getLocacaoList());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(ItemIndisponivelException.class)
    public ResponseEntity<Map<String, Object>> handleItemIndisponivel(ItemIndisponivelException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Item indisponível");
        body.put("message", ex.getMessage());
        body.put("dtDisponivel", ex.getDtDisponivel());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}
