package com.attornatus.repositoryTest;

import com.attornatus.entities.Pessoa;
import com.attornatus.repository.PessoaRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PessoaRepositoryTest {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Test
    public void testSalvarPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Carlos");
        pessoa.setDataNascimento(LocalDate.of(1990, 8, 25));

        pessoaRepository.save(pessoa);

        assertNotNull(pessoa.getId());
    }

    @Test
    public void testConsultarPessoaPorNome() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Diana");
        pessoa.setDataNascimento(LocalDate.of(1987, 6, 10));

        pessoaRepository.save(pessoa);

        String nomeBuscado = "Diana";
        List<Pessoa> pessoas = pessoaRepository.findByNome(nomeBuscado);

        assertNotNull(pessoas);
        assertEquals(1, pessoas.size());
        assertEquals(nomeBuscado, pessoas.get(0).getNome());
    }
}

