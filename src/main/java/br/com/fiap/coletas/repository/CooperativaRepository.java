package br.com.fiap.coletas.repository;

import br.com.fiap.coletas.model.Cooperativa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CooperativaRepository extends JpaRepository<Cooperativa, Long> {
    Optional<Cooperativa> findByNomeCooperativa(String nomeCooperativa);
}
