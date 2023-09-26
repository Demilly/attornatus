package br.com.attornatus.model.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PessoaDto {

    private String nome;
    private Date dataNascimento;
    private List<EnderecoDto> enderecos;

}
