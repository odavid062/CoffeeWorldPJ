package com.coffeworld.backend.model;

import com.coffeworld.backend.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedido_seq")
    @SequenceGenerator(name = "pedido_seq", sequenceName = "pedido_seq", allocationSize = 1)
    private Long id;

    private String cpfCliente;
    private Double valorTotal;
    private String formaPagamento;

    private LocalDateTime dataHoraPedido;
    private LocalDateTime previsaoEntrega;

    @Enumerated(EnumType.STRING)
    private StatusPedido status; // PENDENTE, EM_PREPARO, FINALIZADO, CANCELADO

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens;

    @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
    private Avaliacao avaliacao;

    private String motivoCancelamento;

    public String observacao;
    
}

