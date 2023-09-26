package br.com.attornatus.controller;

import br.com.attornatus.model.dto.PessoaDto;
import br.com.attornatus.service.PessoaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;

public class PessoaControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PessoaService pessoaService;

    @InjectMocks
    private PessoaController pessoaController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(pessoaController).build();
    }

    @Test
    public void testCriaPessoa() throws Exception {
        PessoaDto pessoaDto = new PessoaDto();
        pessoaDto.setNome("João");

        when(pessoaService.criarPessoa(any(PessoaDto.class))).thenReturn(pessoaDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/pessoas")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("João"));

        verify(pessoaService, times(1)).criarPessoa(any(PessoaDto.class));
    }

    @Test
    public void testEditaPessoa() throws Exception {
        Long pessoaId = 1L;
        PessoaDto pessoaDto = new PessoaDto();
        pessoaDto.setNome("Maria");

        when(pessoaService.editarPessoa(eq(pessoaId), any(PessoaDto.class))).thenReturn(pessoaDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/pessoas/{id}", pessoaId)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Maria"));

        verify(pessoaService, times(1)).editarPessoa(eq(pessoaId), any(PessoaDto.class));
    }

    @Test
    public void testConsultaPessoa() throws Exception {
        Long pessoaId = 1L;
        PessoaDto pessoaDto = new PessoaDto();
        pessoaDto.setNome("Carlos");

        when(pessoaService.consultarPessoa(eq(pessoaId))).thenReturn(pessoaDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/pessoas/{id}", pessoaId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Carlos"));

        verify(pessoaService, times(1)).consultarPessoa(eq(pessoaId));
    }

    @Test
    public void testDefiniEnderecoPrincipal() throws Exception {
        Long pessoaId = 1L;
        Long enderecoId = 2L;

        mockMvc.perform(MockMvcRequestBuilders.post("/pessoas/{id}/endereco/principal/{enderecoId}", pessoaId, enderecoId))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(pessoaService, times(1)).definirEnderecoPrincipal(eq(pessoaId), eq(enderecoId));
    }
}
