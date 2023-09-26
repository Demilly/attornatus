package br.com.attornatus.service;

import br.com.attornatus.model.dto.EnderecoDto;
import br.com.attornatus.model.dto.PessoaDto;
import br.com.attornatus.model.entity.Endereco;
import br.com.attornatus.model.entity.Pessoa;
import br.com.attornatus.model.mapper.EnderecoMapper;
import br.com.attornatus.model.mapper.PessoaMapper;
import br.com.attornatus.repository.EnderecoRepository;
import br.com.attornatus.repository.PessoaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class PessoaServiceImplTest {

    @InjectMocks
    private PessoaServiceImpl pessoaService;

    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private EnderecoRepository enderecoRepository;

    @Mock
    private PessoaMapper pessoaMapper;

    @Mock
    private EnderecoMapper enderecoMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCriarPessoa() {
        PessoaDto pessoaDto = new PessoaDto();
        Pessoa pessoaSimulada = new Pessoa();

        when(pessoaMapper.toPessoa(pessoaDto)).thenReturn(pessoaSimulada);
        when(pessoaRepository.save(pessoaSimulada)).thenReturn(pessoaSimulada);
        when(pessoaMapper.toPessoaDto(pessoaSimulada)).thenReturn(pessoaDto);

        PessoaDto result = pessoaService.criarPessoa(pessoaDto);
        assertNotNull(result);
    }

    @Test
    public void testEditarPessoaExistente() {
        Long id = 1L;
        PessoaDto pessoaDto = new PessoaDto();
        Pessoa pessoaSimulada = new Pessoa();

        when(pessoaRepository.findById(id)).thenReturn(Optional.of(pessoaSimulada));
        when(pessoaMapper.toPessoa(pessoaDto)).thenReturn(pessoaSimulada);
        when(pessoaRepository.save(pessoaSimulada)).thenReturn(pessoaSimulada);
        when(pessoaMapper.toPessoaDto(pessoaSimulada)).thenReturn(pessoaDto);

        PessoaDto result = pessoaService.editarPessoa(id, pessoaDto);

        assertNotNull(result);
    }

    @Test
    public void testConsultarPessoaExistente() {
        Long id = 1L;
        Pessoa pessoaSimulada = new Pessoa();

        when(pessoaRepository.findById(id)).thenReturn(Optional.of(pessoaSimulada));
        when(pessoaMapper.toPessoaDto(pessoaSimulada)).thenReturn(new PessoaDto());

        PessoaDto result = pessoaService.consultarPessoa(id);
        assertNotNull(result);
    }


    @Test
    public void testCriarEndereco() {
        Long pessoaId = 1L;
        EnderecoDto enderecoDto = new EnderecoDto();
        Pessoa pessoaSimulada = new Pessoa();
        Endereco enderecoSimulado = new Endereco();

        when(pessoaRepository.findById(pessoaId)).thenReturn(Optional.of(pessoaSimulada));
        when(enderecoMapper.toEndereco(enderecoDto)).thenReturn(enderecoSimulado);
        when(enderecoRepository.save(enderecoSimulado)).thenReturn(enderecoSimulado);
        when(enderecoMapper.toEnderecoDto(enderecoSimulado)).thenReturn(enderecoDto);

        EnderecoDto result = pessoaService.criarEndereco(pessoaId, enderecoDto);

        assertNotNull(result);
    }
}