package br.com.fiap.coletas.dto;


import br.com.fiap.coletas.model.UsuarioRole;

public record UsuarioExibicaoDTO(
        Long usuarioId,
        String nome,
        String email,
        UsuarioRole role
) {
}
