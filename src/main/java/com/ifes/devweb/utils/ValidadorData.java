package com.ifes.devweb.utils;

import com.ifes.devweb.execption.DataInvalidaException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class ValidadorData {
    public static LocalDate validar(String data){
        System.out.println("Recebido: >" + data + "<");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                .withResolverStyle(ResolverStyle.STRICT);

        try {
            LocalDate parsed = LocalDate.parse(data);
            System.out.println("Data parseada com sucesso: " + parsed);
            return parsed;


        } catch (DateTimeParseException e) {
            System.out.println("Erro ao parsear: " + e.getMessage());
            throw new DataInvalidaException("Data inválida: " + data);
        }
    }
}
