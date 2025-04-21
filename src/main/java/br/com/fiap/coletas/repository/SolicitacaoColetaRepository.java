package br.com.fiap.coletas.repository;

import br.com.fiap.coletas.model.SolicitacaoColeta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitacaoColetaRepository extends JpaRepository<SolicitacaoColeta, Long> {
}
