package br.com.fiap.coletas.dto;

import br.com.fiap.coletas.model.Empresa;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmpresaExibicaoDTO (
        Long idEmpresa,

        @NotBlank(message = "Nome da empresa é obrigatório!")
        String nomeEmpresa,

        @NotBlank(message = "Endereço é obrigatório")
        String endereco,

        @NotBlank(message = "O telefone é obrigatório")
        String telefone,

        @NotBlank(message = "Email é obrigatório!")
        @Email(message = "Email está com formato inválido")
        String email

) {
    public EmpresaExibicaoDTO (Empresa empresa) {
        this (
                empresa.getIdEmpresa(),
                empresa.getNomeEmpresa(),
                empresa.getEndereco(),
                empresa.getTelefone(),
                empresa.getEmail()
        );
    }
}
