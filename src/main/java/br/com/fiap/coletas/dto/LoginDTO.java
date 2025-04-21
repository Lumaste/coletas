package br.com.fiap.coletas.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginDTO(
        @NotBlank(message = "Email do usuário é obrigatório!")
        @Email(message = "Formato do email inválido!")
        String email,

        @NotBlank(message = "Senha do usuário é obrigatória!")
        @Size(min = 6, max = 6, message = "Senha deve ter exatamente 6 caracteres!")
        String senha
) {
}
