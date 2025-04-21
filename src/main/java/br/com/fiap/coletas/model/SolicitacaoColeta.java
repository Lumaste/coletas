package br.com.fiap.coletas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_solicitacao_coleta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SolicitacaoColeta {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_SOLICITACAO"
    )
    @SequenceGenerator(
            name = "SEQ_SOLICITACAO",
            sequenceName = "SEQ_SOLICITACAO",
            allocationSize = 1
    )
    private Long idSolicitacao;

//    @Column(name = "id_usuario")
//    private Long idUsuario;

//    @Column(name = "data_solicitacao")
//    private LocalDate dataSolicitacao;

    @Column(name = "descricao_material")
    private String descricaoMaterial;

    @Column(name = "endereco_solicitacao")
    private String enderecoSolicitacao;

    @Column(name = "status_solicitacao")
    private String statusSolicitacao;

    @Column(name = "descricao_notificacao")
    private String descricaoNotificacao;

    @NotNull
    @Column(name = "id_cooperativa")
    private Long idCooperativa;

    @NotNull
    @Column(name = "id_empresa")
    private Long idEmpresa;

    public @NotNull Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(@NotNull Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getDescricaoNotificacao() {
        return descricaoNotificacao;
    }

    public void setDescricaoNotificacao(String descricaoNotificacao) {
        this.descricaoNotificacao = descricaoNotificacao;
    }

    public @NotNull Long getIdCooperativa() {
        return idCooperativa;
    }

    public void setIdCooperativa(@NotNull Long idCooperativa) {
        this.idCooperativa = idCooperativa;
    }


    public Long getIdSolicitacao() {
        return idSolicitacao;
    }

    public void setIdSolicitacao(Long idSolicitacao) {
        this.idSolicitacao = idSolicitacao;
    }

    public String getDescricaoMaterial() {
        return descricaoMaterial;
    }

    public void setDescricaoMaterial(String descricaoMaterial) {
        this.descricaoMaterial = descricaoMaterial;
    }

    public String getEnderecoSolicitacao() {
        return enderecoSolicitacao;
    }

    public void setEnderecoSolicitacao(String enderecoSolicitacao) {
        this.enderecoSolicitacao = enderecoSolicitacao;
    }

    public String getStatusSolicitacao() {
        return statusSolicitacao;
    }

    public void setStatusSolicitacao(String statusSolicitacao) {
        this.statusSolicitacao = statusSolicitacao;
    }
}
