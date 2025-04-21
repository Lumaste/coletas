package br.com.fiap.coletas.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tbl_cooperativas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Cooperativa {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_COOPERATIVA"
    )
    @SequenceGenerator(
            name = "SEQ_COOPERATIVA",
            sequenceName = "SEQ_COOPERATIVA",
            allocationSize = 1
    )
    @Column(name = "id_cooperativa")
    private Long idCooperativa;

    @Column(name = "nome")
    private String nomeCooperativa;

    @Column(name = "endereco")
    private String enderecoCooperativa;

    @Column(name = "telefone")
    private String telefoneCooperativa;

    @Column(name = "email")
    private String emailCooperativa;

    public Long getIdCooperativa() {
        return idCooperativa;
    }

    public void setIdCooperativa(Long idCooperativa) {
        this.idCooperativa = idCooperativa;
    }

    public String getNomeCooperativa() {
        return nomeCooperativa;
    }

    public void setNomeCooperativa(String nomeCooperativa) {
        this.nomeCooperativa = nomeCooperativa;
    }

    public String getEnderecoCooperativa() {
        return enderecoCooperativa;
    }

    public void setEnderecoCooperativa(String enderecoCooperativa) {
        this.enderecoCooperativa = enderecoCooperativa;
    }

    public String getTelefoneCooperativa() {
        return telefoneCooperativa;
    }

    public void setTelefoneCooperativa(String telefoneCooperativa) {
        this.telefoneCooperativa = telefoneCooperativa;
    }

    public String getEmailCooperativa() {
        return emailCooperativa;
    }

    public void setEmailCooperativa(String emailCooperativa) {
        this.emailCooperativa = emailCooperativa;
    }
}
