package com.attornatus.serviceTest;

import com.attornatus.entities.Pessoa;
import com.attornatus.exception.ResourceNotFoundException;
import com.attornatus.repository.PessoaRepository;
import com.attornatus.service.PessoaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PessoaServiceTest {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Test
    public void testCriarPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("João");
        pessoa.setDataNascimento(LocalDate.of(1990, 5, 15));

        Pessoa pessoaCriada = pessoaService.criarPessoa(pessoa);

        assertNotNull(pessoaCriada.getId());
        assertEquals("João", pessoaCriada.getNome());
    }

    @Test
    public void testConsultarPessoaPorId() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Maria");
        pessoa.setDataNascimento(LocalDate.of(1985, 3, 20));

        Pessoa pessoaCriada = pessoaRepository.save(pessoa);

        Long pessoaId = pessoaCriada.getId();

        Pessoa pessoaConsultada = pessoaService.consultarPessoaPorId(pessoaId);

        assertNotNull(pessoaConsultada);
        assertEquals("Maria", pessoaConsultada.getNome());
    }

    @Test
    public void testConsultarPessoaPorIdNaoEncontrada() {
        Long pessoaIdNaoExistente = 999L;
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            pessoaService.consultarPessoaPorId(pessoaIdNaoExistente);
        });
    }

}

