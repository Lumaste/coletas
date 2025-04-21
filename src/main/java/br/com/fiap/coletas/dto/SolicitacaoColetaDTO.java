package br.com.fiap.coletas.dto;

import br.com.fiap.coletas.model.SolicitacaoColeta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record SolicitacaoColetaDTO(
        Long idSolicitacao,

//        @NotBlank(message = "O ID do usuário é obrigatório!")
//        Long idUsuario,

//        @NotNull
//        LocalDate dataSolicitacao,

        @NotBlank(message = "Descrição obrigatória!")
        String descricaoMaterial,

        @NotBlank(message = "Endereço do local é obrigatório")
        String enderecoSolicitacao,

        String statusSolicitacao,
        String descricaoNotificacao,

        @NotBlank(message = "O ID do da Cooperativa é obrigatório!")
        Long idCooperativa,

        @NotBlank(message = "O ID da Empresa é obrigatório!")
        Long idEmpresa
) {
    public SolicitacaoColetaDTO (SolicitacaoColeta solicitacaoColeta) {
        this(
                solicitacaoColeta.getIdSolicitacao(),
//                solicitacaoColeta.getIdUsuario(),
//                solicitacaoColeta.getDataSolicitacao(),
                solicitacaoColeta.getDescricaoMaterial(),
                solicitacaoColeta.getEnderecoSolicitacao(),
                solicitacaoColeta.getStatusSolicitacao(),
                solicitacaoColeta.getDescricaoNotificacao(),
                solicitacaoColeta.getIdCooperativa(),
                solicitacaoColeta.getIdEmpresa()
        );
    }

}
