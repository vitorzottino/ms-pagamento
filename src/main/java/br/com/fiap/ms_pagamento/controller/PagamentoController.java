package br.com.fiap.ms_pagamento.controller;

import br.com.fiap.ms_pagamento.dto.PagamentoDTO;
import br.com.fiap.ms_pagamento.service.PagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/pagamentos")
@Tag(name = "Pagamentos", description = "Controller/Publisher dos Pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @Operation(description = "Listar todos os pagamentos", summary = "Retorna uma lista de pagamentos", responses = {@ApiResponse(description = "Ok", responseCode = "200")})
    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<PagamentoDTO>> findAll(@PageableDefault(size = 10) Pageable pageable) {
        Page<PagamentoDTO> dto = pagamentoService.findAll(pageable);
        return ResponseEntity.ok(dto);
    }

    @Operation(description = "Retorna um pagamento a partir do identificador {id}", summary = "Retorna um pagamento por id", responses = {@ApiResponse(description = "Ok", responseCode = "200"), @ApiResponse(description = "Not found", responseCode = "404")})
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PagamentoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(pagamentoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PagamentoDTO> insert(@RequestBody @Valid PagamentoDTO dto) {
        dto = pagamentoService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pagamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagamentoDTO> update(@PathVariable @NotNull Long id, @RequestBody @Valid PagamentoDTO dto) {
        dto = pagamentoService.update(id, dto);
        return ResponseEntity.ok(dto);
    }

}
