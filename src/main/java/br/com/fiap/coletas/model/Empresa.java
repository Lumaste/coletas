package br.com.fiap.coletas.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_empresas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Empresa {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_EMPRESAS"
    )
    @SequenceGenerator(
            name = "SEQ_EMPRESAS",
            sequenceName = "SEQ_EMPRESAS",
            allocationSize = 1
    )
    @Column(name = "id_empresa")
    private Long idEmpresa;

    @Column(name = "nome_empresa")
    private String nomeEmpresa;

    private String endereco;
    private String telefone;

    private String email;


    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
