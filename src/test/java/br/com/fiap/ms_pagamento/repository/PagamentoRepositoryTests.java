package br.com.fiap.ms_pagamento.repository;

import br.com.fiap.ms_pagamento.model.Pagamento;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class PagamentoRepositoryTests {

    @Autowired
    private PagamentoRepository pagamentoRepository;


    @Test
    public void deleteShouldDeleteObjectWhenIdExists(){
        //Arrange
        Long existingId = 1L;
        //Act
        pagamentoRepository.deleteById(existingId);
        //Assert
        Optional<Pagamento> result = pagamentoRepository.findById(existingId);
        //Test if the optional has a object
        Assertions.assertFalse(result.isPresent());
    }

}
