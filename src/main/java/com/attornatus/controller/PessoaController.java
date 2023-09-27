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
@RequestMapping("/api/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<Pessoa> criarPessoa(@RequestBody Pessoa pessoa) {
        Pessoa pessoaCriada = pessoaService.criarPessoa(pessoa);
        return new ResponseEntity<>(pessoaCriada, HttpStatus.CREATED);
    }

    @PutMapping("/{pessoaId}")
    public ResponseEntity<Pessoa> editarPessoa(
            @PathVariable Long pessoaId,
            @RequestBody Pessoa pessoaAtualizada) {
        Pessoa pessoaEditada = pessoaService.editarPessoa(pessoaId, pessoaAtualizada);
        return new ResponseEntity<>(pessoaEditada, HttpStatus.OK);
    }

    @GetMapping("/{pessoaId}")
    public ResponseEntity<Pessoa> consultarPessoaPorId(@PathVariable Long pessoaId) {
        Pessoa pessoa = pessoaService.consultarPessoaPorId(pessoaId);
        return new ResponseEntity<>(pessoa, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> listarPessoas() {
        List<Pessoa> pessoas = pessoaService.listarPessoas();
        return new ResponseEntity<>(pessoas, HttpStatus.OK);
    }

    @PostMapping("/{pessoaId}/endereco-principal/{enderecoId}")
    public ResponseEntity<Pessoa> definirEnderecoPrincipal(
            @PathVariable Long pessoaId,
            @PathVariable Long enderecoId) {
        Pessoa pessoaComEnderecoPrincipal = pessoaService.definirEnderecoPrincipal(pessoaId, enderecoId);
        return new ResponseEntity<>(pessoaComEnderecoPrincipal, HttpStatus.OK);
    }

    @PostMapping("/{pessoaId}/enderecos")
    public ResponseEntity<Endereco> criarEndereco(
            @PathVariable Long pessoaId,
            @RequestBody Endereco endereco) {
        Endereco enderecoCriado = enderecoService.criarEndereco(pessoaId, endereco);
        return new ResponseEntity<>(enderecoCriado, HttpStatus.CREATED);
    }

    @GetMapping("/{pessoaId}/enderecos")
    public ResponseEntity<List<Endereco>> listarEnderecosPessoa(@PathVariable Long pessoaId) {
        List<Endereco> enderecos = enderecoService.listarEnderecosPessoa(pessoaId);
        return new ResponseEntity<>(enderecos, HttpStatus.OK);
    }
}