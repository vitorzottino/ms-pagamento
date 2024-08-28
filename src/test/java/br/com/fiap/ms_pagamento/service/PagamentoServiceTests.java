package br.com.fiap.ms_pagamento.service;

import br.com.fiap.ms_pagamento.repository.PagamentoRepository;
import exception.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class PagamentoServiceTests {

    @InjectMocks
    private PagamentoService pagamentoService;

    @Mock
    private PagamentoRepository pagamentoRepository;

    private final Long existingId = 1L;
    private final Long nonExistingId = 10L;


    @BeforeEach
    void setup() {

        Mockito.when(pagamentoRepository.existsById(existingId)).thenReturn(true);
        Mockito.when(pagamentoRepository.existsById(nonExistingId)).thenReturn(false);
        Mockito.doNothing().when(pagamentoRepository).deleteById(existingId);
    }

    @Test
    @DisplayName("delete deveria fazer nada quando ID  existe")
    public void deleteShouldDoNothingWhenIdExists() {
        Assertions.assertDoesNotThrow(() -> pagamentoService.delete(existingId));
    }

    @Test
    @DisplayName("delete deveria lancar excecao quando id nao existe")
    public void deleteShouldThrowExceptionWhenIdDoesNotExist() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> pagamentoService.delete(nonExistingId));
    }
}
