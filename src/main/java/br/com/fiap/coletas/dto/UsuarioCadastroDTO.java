package br.com.fiap.coletas.dto;

import br.com.fiap.coletas.model.UsuarioRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioCadastroDTO(
      Long usuarioId,

      @NotBlank(message = "O nome do usuário é obrigatório")
      String nome,

      @NotBlank(message = "O email do usuário é obrigatório!")
      @Email(message = "O email é invalido!")
      String email,


      @NotBlank(message = "A senha é obrigatória")
      @Size(min = 6, max = 6, message = "A senha deve ter exatamente 6 caracteres!")
      String senha,

      UsuarioRole role
) {
}
