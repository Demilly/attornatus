package br.com.attornatus.service;

import br.com.attornatus.model.dto.EnderecoDto;
import br.com.attornatus.model.dto.PessoaDto;
import br.com.attornatus.model.entity.Endereco;
import br.com.attornatus.model.entity.Pessoa;
import br.com.attornatus.model.mapper.EnderecoMapper;
import br.com.attornatus.model.mapper.PessoaMapper;
import br.com.attornatus.repository.EnderecoRepository;
import br.com.attornatus.repository.PessoaRepository;
import br.com.attornatus.service.exception.PessoaNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository pessoaRepository;
    private final EnderecoRepository enderecoRepository;
    private final PessoaMapper pessoaMapper;
    private final EnderecoMapper enderecoMapper;

    @Override
    public PessoaDto criarPessoa(PessoaDto pessoaDto) {

        Pessoa novaPessoa = pessoaMapper.toPessoa(pessoaDto);

        if (pessoaDto.getEnderecos() != null) {
            novaPessoa.getEnderecos().clear();
            pessoaDto.getEnderecos()
                    .stream()
                    .map(enderecoMapper::toEndereco)
                    .forEach(novaPessoa::adicionarEndereco);
        }

        Pessoa pessoaSalva = pessoaRepository.save(novaPessoa);

        return pessoaMapper.toPessoaDto(pessoaSalva);
    }

    @Override
    public PessoaDto editarPessoa(Long id, PessoaDto pessoaDto) {
        Optional<Pessoa> pessoaExistente = pessoaRepository.findById(id);

        Pessoa pessoa = pessoaMapper.toPessoa(pessoaDto);

        if (pessoaExistente.isPresent()) {
            Pessoa pessoaAtualizada = pessoaExistente.get();
            pessoaAtualizada.setNome(pessoa.getNome());
            pessoaAtualizada.setDataNascimento(pessoa.getDataNascimento());
            Pessoa pessoaEditada = pessoaRepository.save(pessoaAtualizada);
            return pessoaMapper.toPessoaDto(pessoaEditada);
        } else {
            throw new PessoaNotFoundException("Pessoa não encontrada com o ID: " + id);
        }
    }

    @Override
    public PessoaDto consultarPessoa(Long id) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new PessoaNotFoundException("Pessoa não encontrada com o ID: " + id));
        return pessoaMapper.toPessoaDto(pessoa);
    }

    @Override
    public List<PessoaDto> listarPessoas() {
        List<Pessoa> pessoas = (List<Pessoa>) pessoaRepository.findAll();
        return pessoas.stream()
                .map(pessoaMapper::toPessoaDto)
                .collect(Collectors.toList());
    }

    @Override
    public EnderecoDto criarEndereco(Long pessoaId, EnderecoDto enderecoDto) {
        Optional<Pessoa> pessoaExistente = pessoaRepository.findById(pessoaId);

        Endereco endereco = enderecoMapper.toEndereco(enderecoDto);

        if (pessoaExistente.isPresent()) {
            Pessoa pessoa = pessoaExistente.get();
            endereco.setPessoa(pessoa);
            Endereco enderecoCriado = enderecoRepository.save(endereco);
            return enderecoMapper.toEnderecoDto(enderecoCriado);
        } else {
            throw new PessoaNotFoundException("Pessoa não encontrada com o ID: " + pessoaId);
        }
    }

    @Override
    public List<EnderecoDto> listarEnderecos(Long pessoaId) {
        Optional<Pessoa> pessoaExistente = pessoaRepository.findById(pessoaId);

        if (pessoaExistente.isPresent()) {
            Pessoa pessoa = pessoaExistente.get();
            List<Endereco> enderecos = pessoa.getEnderecos();

            return enderecos.stream()
                    .map(enderecoMapper::toEnderecoDto)
                    .collect(Collectors.toList());
        } else {
            throw new PessoaNotFoundException("Pessoa não encontrada com o ID: " + pessoaId);
        }
    }

    @Override
    public void definirEnderecoPrincipal(Long pessoaId, Long enderecoId) {
        Optional<Pessoa> pessoaExistente = pessoaRepository.findById(pessoaId);

        if (pessoaExistente.isPresent()) {
            Pessoa pessoa = pessoaExistente.get();
            List<Endereco> enderecos = pessoa.getEnderecos();

            for (Endereco endereco : enderecos) {
                if (endereco.getId().equals(enderecoId)) {
                    endereco.setPrincipal(true);
                } else {
                    endereco.setPrincipal(false);
                }
            }

            enderecoRepository.saveAll(enderecos);
        } else {
            throw new PessoaNotFoundException("Pessoa não encontrada com o ID: " + pessoaId);
        }
    }

}




