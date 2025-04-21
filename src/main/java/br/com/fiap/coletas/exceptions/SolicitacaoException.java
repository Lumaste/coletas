package br.com.fiap.coletas.exceptions;

public class SolicitacaoException extends RuntimeException{
    public SolicitacaoException(String mensagem) {
        super(mensagem);
    }
}
