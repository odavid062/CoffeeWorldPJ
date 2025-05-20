package com.coffeworld.backend.repository;

import com.coffeworld.backend.enums.StatusPedido;
import com.coffeworld.backend.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByStatus(StatusPedido status);
}
