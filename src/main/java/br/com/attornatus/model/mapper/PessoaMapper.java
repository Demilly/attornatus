package br.com.attornatus.model.mapper;

import br.com.attornatus.model.dto.PessoaDto;
import br.com.attornatus.model.entity.Pessoa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PessoaMapper {

    Pessoa toPessoa(PessoaDto pessoaDto);

    PessoaDto toPessoaDto(Pessoa pessoa);

}
