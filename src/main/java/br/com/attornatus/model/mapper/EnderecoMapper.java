package br.com.attornatus.model.mapper;

import br.com.attornatus.model.dto.EnderecoDto;
import br.com.attornatus.model.entity.Endereco;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    Endereco toEndereco(EnderecoDto enderecoDto);

    EnderecoDto toEnderecoDto(Endereco endereco);

    List<Endereco> toEndereco(List<EnderecoDto> enderecos);
}
