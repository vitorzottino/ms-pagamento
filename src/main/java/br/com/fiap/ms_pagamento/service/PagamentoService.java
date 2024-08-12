package br.com.fiap.ms_pagamento.service;

import br.com.fiap.ms_pagamento.dto.PagamentoDTO;
import br.com.fiap.ms_pagamento.model.Pagamento;
import br.com.fiap.ms_pagamento.repository.PagamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Transactional(readOnly = true)
    public Page<PagamentoDTO> findAll(Pageable pageable) {
        Page<Pagamento> page = pagamentoRepository.findAll(pageable);
        return page.map(PagamentoDTO::new);
    }

    @Transactional(readOnly = true)
    public PagamentoDTO findById(Long id) {

        return new PagamentoDTO(pagamentoRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Recurso não encontrado")));
    }

    @Transactional
    public PagamentoDTO insert(PagamentoDTO dto) {
        Pagamento entity = new Pagamento();
        copyToDtoEnitty(dto, entity);
        return new PagamentoDTO(pagamentoRepository.save(entity));
    }

    @Transactional
    public void delete(Long id) {
        if (!pagamentoRepository.existsById(id)) {
            throw new EntityNotFoundException("Recurso não encontrado");
        }
        try {
            pagamentoRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Recurso não encontrado");
        }
    }

    private void copyToDtoEnitty(PagamentoDTO dto, Pagamento entity) {
        entity.setValor(dto.getValor());
        entity.setNome(dto.getNome());
        entity.setNumeroDoCartao(dto.getNumeroDoCartao());
        entity.setValidade(dto.getValidade());
        entity.setCodigoDeSeguranca(dto.getCodigoDeSeguranca());
        entity.setStatus(dto.getStatus());
        entity.setPedidoId(dto.getPedidoId());
        entity.setFormaDePagamentoId(dto.getFormaDePagamentoId());
    }
}
