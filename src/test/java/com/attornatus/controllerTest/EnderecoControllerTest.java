package com.attornatus.controllerTest;

import com.attornatus.controller.EnderecoController;
import com.attornatus.entities.Endereco;
import com.attornatus.service.EnderecoService;
import com.attornatus.service.PessoaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(EnderecoController.class)
public class EnderecoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EnderecoService enderecoService;

    @MockBean
    private PessoaService pessoaService;

    @Test
    public void testCriarEndereco() throws Exception {
        Long pessoaId = 1L;
        Endereco endereco = new Endereco();
        endereco.setLogradouro("Rua XYZ");
        endereco.setCep("12345-678");
        endereco.setNumero("456");
        endereco.setCidade("São Paulo");

        Mockito.when(enderecoService.criarEndereco(Mockito.anyLong(), Mockito.any(Endereco.class))).thenReturn(endereco);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/enderecos/{pessoaId}", pessoaId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(endereco)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.logradouro").value("Rua XYZ"));
    }

    @Test
    public void testListarEnderecosPessoa() throws Exception {
        Long pessoaId = 2L;
        List<Endereco> enderecos = new ArrayList<>();
        Endereco endereco1 = new Endereco();
        endereco1.setLogradouro("Rua ABC");
        endereco1.setCep("54321-987");
        endereco1.setNumero("123");
        endereco1.setCidade("Rio de Janeiro");
        enderecos.add(endereco1);

        Endereco endereco2 = new Endereco();
        endereco2.setLogradouro("Rua DEF");
        endereco2.setCep("98765-432");
        endereco2.setNumero("789");
        endereco2.setCidade("Salvador");
        enderecos.add(endereco2);

        Mockito.when(enderecoService.listarEnderecosPessoa(pessoaId)).thenReturn(enderecos);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/enderecos/{pessoaId}", pessoaId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].logradouro").value("Rua ABC"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].logradouro").value("Rua DEF"));
    }

    private static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

