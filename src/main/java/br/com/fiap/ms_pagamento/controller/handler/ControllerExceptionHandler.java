package br.com.fiap.ms_pagamento.controller.handler;

import br.com.fiap.ms_pagamento.dto.CustomErrorDTO;
import br.com.fiap.ms_pagamento.dto.ValidationErrorDTO;
import com.sun.net.httpserver.HttpsServer;
import exception.DatabaseException;
import exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)//custom exception
    public ResponseEntity<CustomErrorDTO> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomErrorDTO errorDTO = new CustomErrorDTO(Instant.now().toString(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(errorDTO);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorDTO> methodArgumentNotValidation(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationErrorDTO validationErrorDTO = new ValidationErrorDTO(Instant.now().toString(), status.value(), "Dados invalidos", request.getRequestURI());
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            validationErrorDTO.addError(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseEntity.status(status).body(validationErrorDTO);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<CustomErrorDTO> databaseException(DatabaseException e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomErrorDTO customErrorDTO = new CustomErrorDTO(Instant.now().toString(), status.value(), e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(customErrorDTO);

    }

}
