package br.com.attornatus.service.exception;

public class PessoaNotFoundException extends RuntimeException {

    public PessoaNotFoundException(String message) {
        super(message);
    }
}
