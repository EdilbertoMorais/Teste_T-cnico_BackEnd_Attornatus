package com.attornatus.controller;

import com.attornatus.entities.Endereco;
import com.attornatus.entities.Pessoa;
import com.attornatus.service.EnderecoService;
import com.attornatus.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private PessoaService pessoaService;

    @PostMapping("/{pessoaId}")
    public ResponseEntity<Endereco> criarEndereco(
            @PathVariable Long pessoaId,
            @RequestBody Endereco endereco) {
        Endereco enderecoCriado = enderecoService.criarEndereco(pessoaId, endereco);
        return new ResponseEntity<>(enderecoCriado, HttpStatus.CREATED);
    }

    @GetMapping("/{pessoaId}")
    public ResponseEntity<List<Endereco>> listarEnderecosPessoa(@PathVariable Long pessoaId) {
        List<Endereco> enderecos = enderecoService.listarEnderecosPessoa(pessoaId);
        return new ResponseEntity<>(enderecos, HttpStatus.OK);
    }

    @PutMapping("/{pessoaId}/principal/{enderecoId}")
    public ResponseEntity<Pessoa> definirEnderecoPrincipal(
            @PathVariable Long pessoaId,
            @PathVariable Long enderecoId) {
        Pessoa pessoaComEnderecoPrincipal = pessoaService.definirEnderecoPrincipal(pessoaId, enderecoId);
        return new ResponseEntity<>(pessoaComEnderecoPrincipal, HttpStatus.OK);
    }
}
