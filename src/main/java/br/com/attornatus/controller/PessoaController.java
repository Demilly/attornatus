package br.com.attornatus.controller;

import br.com.attornatus.model.dto.EnderecoDto;
import br.com.attornatus.model.dto.PessoaDto;
import br.com.attornatus.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
@CrossOrigin("*")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaService pessoaService;

    @PostMapping
    public PessoaDto criaPessoa(@RequestBody PessoaDto pessoaDto) {
        return pessoaService.criarPessoa(pessoaDto);
    }

    @PutMapping("/{id}")
    public PessoaDto editaPessoa(@PathVariable Long id, @RequestBody PessoaDto pessoaDto) {
        return pessoaService.editarPessoa(id, pessoaDto);
    }

    @GetMapping("/{id}")
    public PessoaDto consultaPessoa(@PathVariable Long id) {
        return pessoaService.consultarPessoa(id);
    }

    @GetMapping
    public List<PessoaDto> listaPessoas() {
        return pessoaService.listarPessoas();
    }

    @PostMapping("/{id}/endereco")
    public EnderecoDto criaEndereco(@PathVariable Long id, @RequestBody EnderecoDto enderecoDto) {
        return pessoaService.criarEndereco(id, enderecoDto);
    }

    @GetMapping("/{id}/enderecos")
    public List<EnderecoDto> listaEnderecos(@PathVariable Long id) {
        return pessoaService.listarEnderecos(id);
    }

    @PostMapping("/{id}/endereco/principal/{enderecoId}")
    public void definiEnderecoPrincipal(@PathVariable Long id, @PathVariable Long enderecoId) {
        pessoaService.definirEnderecoPrincipal(id, enderecoId);
    }
}
