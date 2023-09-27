package com.attornatus.service;

import com.attornatus.entities.Endereco;
import com.attornatus.entities.Pessoa;
import com.attornatus.exception.ResourceNotFoundException;
import com.attornatus.repository.EnderecoRepository;
import com.attornatus.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Pessoa criarPessoa(Pessoa pessoa) {
        if (pessoa == null) {
            throw new IllegalArgumentException("Pessoa não pode ser nula.");
        }

        if (pessoa.getDataNascimento() != null && pessoa.getDataNascimento().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data de nascimento não pode ser no futuro.");
        }

        if (pessoa.getNome() == null || pessoa.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome da pessoa não pode estar vazio.");
        }

        return pessoaRepository.save(pessoa);
    }

    public Pessoa editarPessoa(Long pessoaId, Pessoa pessoaAtualizada) {
        Pessoa pessoaExistente = pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada com o ID: " + pessoaId));

        if (pessoaAtualizada.getNome() == null || pessoaAtualizada.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome da pessoa não pode estar vazio.");
        }

        if (pessoaAtualizada.getDataNascimento() != null && pessoaAtualizada.getDataNascimento().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data de nascimento não pode ser no futuro.");
        }

        pessoaExistente.setNome(pessoaAtualizada.getNome());
        pessoaExistente.setDataNascimento(pessoaAtualizada.getDataNascimento());

        return pessoaRepository.save(pessoaExistente);
    }

    public Pessoa consultarPessoaPorId(Long pessoaId) {
        return pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada com o ID: " + pessoaId));
    }

    public List<Pessoa> listarPessoas() {
        return pessoaRepository.findAll();
    }

    public Pessoa definirEnderecoPrincipal(Long pessoaId, Long enderecoId) {
        Pessoa pessoa = pessoaRepository.findById(pessoaId).orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada"));
        Endereco endereco = enderecoRepository.findById(enderecoId).orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado"));

        if (!pessoa.getEnderecos().contains(endereco)) {
            throw new IllegalArgumentException("O endereço não pertence à pessoa.");
        }

        pessoa.setEnderecoPrincipal(endereco);

        return pessoaRepository.save(pessoa);
    }
}
