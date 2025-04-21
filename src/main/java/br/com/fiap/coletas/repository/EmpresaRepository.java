package br.com.fiap.coletas.repository;

import br.com.fiap.coletas.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    Optional<Empresa> findByNomeEmpresa(String nomeEmpresa);
}
