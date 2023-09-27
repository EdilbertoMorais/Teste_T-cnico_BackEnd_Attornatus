package com.attornatus.entidades;

import com.attornatus.entities.Pessoa;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class PessoaTest {

    @Test
    public void testCriacaoPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("Alice");
        pessoa.setDataNascimento(LocalDate.of(1990, 5, 15));

        assertEquals(1L, pessoa.getId().longValue());
        assertEquals("Alice", pessoa.getNome());
        assertEquals(LocalDate.of(1990, 5, 15), pessoa.getDataNascimento());
    }
}