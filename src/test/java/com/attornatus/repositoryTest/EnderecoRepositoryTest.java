package com.attornatus.repositoryTest;

import com.attornatus.entities.Endereco;
import com.attornatus.entities.Pessoa;
import com.attornatus.repository.EnderecoRepository;
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
public class EnderecoRepositoryTest {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Test
    public void testSalvarEndereco() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Alice");
        pessoa.setDataNascimento(LocalDate.of(1990, 5, 15));
        pessoaRepository.save(pessoa);

        Endereco endereco = new Endereco();
        endereco.setLogradouro("Rua XYZ");
        endereco.setCep("12345-678");
        endereco.setNumero("456");
        endereco.setCidade("São Paulo");
        endereco.setPessoa(pessoa);

        enderecoRepository.save(endereco);

        assertNotNull(endereco.getId());
    }

    @Test
    public void testListarEnderecosPorPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Bob");
        pessoa.setDataNascimento(LocalDate.of(1985, 3, 20));
        pessoaRepository.save(pessoa);

        Endereco endereco1 = new Endereco();
        endereco1.setLogradouro("Rua ABC");
        endereco1.setCep("54321-987");
        endereco1.setNumero("123");
        endereco1.setCidade("Rio de Janeiro");
        endereco1.setPessoa(pessoa);

        Endereco endereco2 = new Endereco();
        endereco2.setLogradouro("Rua DEF");
        endereco2.setCep("98765-432");
        endereco2.setNumero("789");
        endereco2.setCidade("Salvador");
        endereco2.setPessoa(pessoa);

        enderecoRepository.save(endereco1);
        enderecoRepository.save(endereco2);

        List<Endereco> enderecos = enderecoRepository.findByPessoaId(pessoa.getId());

        assertNotNull(enderecos);
        assertEquals(2, enderecos.size());
    }
}
