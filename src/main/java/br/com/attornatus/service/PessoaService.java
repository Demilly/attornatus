package br.com.attornatus.service;

import br.com.attornatus.model.dto.EnderecoDto;
import br.com.attornatus.model.dto.PessoaDto;

import java.util.List;

public interface PessoaService {

    PessoaDto criarPessoa(PessoaDto pessoaDto);

    PessoaDto editarPessoa(Long id, PessoaDto pessoaDto);

    PessoaDto consultarPessoa(Long id);

    List<PessoaDto> listarPessoas();

    EnderecoDto criarEndereco(Long pessoaId, EnderecoDto enderecoDto);

    List<EnderecoDto> listarEnderecos(Long pessoaId);

    void definirEnderecoPrincipal(Long pessoaId, Long enderecoId);

}




