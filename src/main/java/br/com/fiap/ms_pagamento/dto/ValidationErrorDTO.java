package br.com.fiap.ms_pagamento.dto;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorDTO extends CustomErrorDTO{

    private List<FieldMessageDTO> errors = new ArrayList<>();

    public ValidationErrorDTO(String timestamp, Integer status, String error, String path){
        super(timestamp, status, error, path);
    }

    public void addError(String fieldname, String message) {
            errors.removeIf(x -> x.getFieldName().equals(fieldname));
            errors.add(new FieldMessageDTO(fieldname, message));
    }
}
