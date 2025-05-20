package com.coffeworld.backend.repository;

import com.coffeworld.backend.model.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    Optional<Avaliacao> findByPedidoId(Long pedidoId);
}
