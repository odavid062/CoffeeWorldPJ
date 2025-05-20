package com.coffeworld.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itempedido_seq")
    @SequenceGenerator(name = "itempedido_seq", sequenceName = "itempedido_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    private Pedido pedido;

    @ManyToOne
    private Produto produto;

    private Integer quantidade;


}

