package br.com.attornatus.model.dto;

import lombok.Data;

@Data
public class EnderecoDto {

    private String logradouro;
    private String cep;
    private String numero;
    private String cidade;
    private boolean principal;
}
