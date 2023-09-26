package br.com.attornatus.repository;

import br.com.attornatus.model.entity.Endereco;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EnderecoRepository extends CrudRepository<Endereco, Long> {
    List<Endereco> findByPessoaId(Long pessoaId);
}
