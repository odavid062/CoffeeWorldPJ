package com.coffeworld.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_seq")
    @SequenceGenerator(name = "produto_seq", sequenceName = "produto_seq", allocationSize = 1)
    private Long id;
    private String nome;
    private String descricao;
    private String imagemUrl;
    private Double preco;
    private Integer tempoPreparoMinutos; // tempo médio de preparo em minutos
    private String categoria;

}
