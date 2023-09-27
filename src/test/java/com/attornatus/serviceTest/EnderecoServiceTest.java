package com.attornatus.serviceTest;

import com.attornatus.entities.Endereco;
import com.attornatus.entities.Pessoa;
import com.attornatus.repository.EnderecoRepository;
import com.attornatus.service.EnderecoService;
import com.attornatus.service.PessoaService;
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
public class EnderecoServiceTest {

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Test
    public void testCriarEndereco() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Carlos");
        pessoa.setDataNascimento(LocalDate.of(1980, 7, 10));
        Pessoa pessoaCriada = pessoaService.criarPessoa(pessoa);

        Endereco endereco = new Endereco();
        endereco.setLogradouro("Rua A");
        endereco.setCep("12345-678");
        endereco.setNumero("123");
        endereco.setCidade("São Paulo");

        Endereco enderecoCriado = enderecoService.criarEndereco(pessoaCriada.getId(), endereco);

        assertNotNull(enderecoCriado.getId());
        assertEquals("Rua A", enderecoCriado.getLogradouro());
    }

    @Test
    public void testListarEnderecosPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Ana");
        pessoa.setDataNascimento(LocalDate.of(1995, 2, 5));
        Pessoa pessoaCriada = pessoaService.criarPessoa(pessoa);

        Endereco endereco1 = new Endereco();
        endereco1.setLogradouro("Rua B");
        endereco1.setCep("54321-987");
        endereco1.setNumero("456");
        endereco1.setCidade("Rio de Janeiro");

        Endereco endereco2 = new Endereco();
        endereco2.setLogradouro("Rua C");
        endereco2.setCep("98765-432");
        endereco2.setNumero("789");
        endereco2.setCidade("Salvador");

        endereco1.setPessoa(pessoaCriada);
        endereco2.setPessoa(pessoaCriada);

        enderecoRepository.save(endereco1);
        enderecoRepository.save(endereco2);

        List<Endereco> enderecos = enderecoService.listarEnderecosPessoa(pessoaCriada.getId());

        assertNotNull(enderecos);
    }
}

