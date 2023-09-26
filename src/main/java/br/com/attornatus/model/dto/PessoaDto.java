package br.com.attornatus.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PessoaDto {

    private String nome;
    private Date dataNascimento;
    private EnderecoDto endereco;

}
