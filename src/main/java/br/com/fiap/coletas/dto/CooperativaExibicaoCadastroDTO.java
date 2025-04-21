package br.com.fiap.coletas.dto;

import br.com.fiap.coletas.model.Cooperativa;

public record CooperativaExibicaoCadastroDTO(
        Long idCooperativa,
        String nomeCooperativa,
        String enderecoCooperativa,
        String telefoneCooperativa,
        String emailCooperativa
) {
    public CooperativaExibicaoCadastroDTO (Cooperativa cooperativa) {
        this (
                cooperativa.getIdCooperativa(),
                cooperativa.getNomeCooperativa(),
                cooperativa.getEnderecoCooperativa(),
                cooperativa.getTelefoneCooperativa(),
                cooperativa.getEmailCooperativa()
        );
    }
}
