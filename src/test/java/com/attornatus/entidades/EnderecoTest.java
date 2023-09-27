package com.attornatus.entidades;

import com.attornatus.entities.Endereco;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class EnderecoTest {

    @Test
    public void testCriacaoEndereco() {
        Endereco endereco = new Endereco();
        endereco.setId(1L);
        endereco.setLogradouro("Rua XYZ");
        endereco.setCep("12345-678");
        endereco.setNumero("456");
        endereco.setCidade("São Paulo");

        assertEquals(1L, endereco.getId().longValue());
        assertEquals("Rua XYZ", endereco.getLogradouro());
        assertEquals("12345-678", endereco.getCep());
        assertEquals("456", endereco.getNumero());
        assertEquals("São Paulo", endereco.getCidade());
    }
}

