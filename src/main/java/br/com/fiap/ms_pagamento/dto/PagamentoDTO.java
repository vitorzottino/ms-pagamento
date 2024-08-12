package br.com.fiap.ms_pagamento.dto;


import br.com.fiap.ms_pagamento.model.Pagamento;
import br.com.fiap.ms_pagamento.model.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PagamentoDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private BigDecimal valor;
    private String nome; // Nome no cartão
    private String numeroDoCartao; // XXXX XXXX XXXX XXXX
    private String validade; // validade do cartão - MM/AA
    private String codigoDeSeguranca; // código de segurança - XXX
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Status status;  // Status do pagamento
    @Column(nullable = false)
    private Long pedidoId;  // Id do pedido
    @Column(nullable = false)
    private Long formaDePagamentoId; // 1 - dinheiro | 2 - cartão | 3 - pix

    public PagamentoDTO(Pagamento entity) {
        id = entity.getId();
        valor = entity.getValor();
        nome = entity.getNome();
        numeroDoCartao = entity.getNumeroDoCartao();
        validade = entity.getValidade();
        codigoDeSeguranca = entity.getCodigoDeSeguranca();
        status = entity.getStatus();
        pedidoId = entity.getPedidoId();
        formaDePagamentoId = entity.getFormaDePagamentoId();


    }
}
