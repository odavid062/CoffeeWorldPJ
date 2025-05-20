package com.coffeworld.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Schema(description = "DTO que representa um produto do cardápio")
public class ProdutoDTO {
    @Schema(description = "ID do produto", example = "1")
    private Long id;

    @Schema(description = "Nome do produto", example = "Café Expresso")
    private String nome;

    @Schema(description = "Descrição do produto", example = "Café curto, forte e encorpado")
    private String descricao;

    @Schema(description = "URL da imagem do produto", example = "https://meusite.com/imagens/cafe.jpg")
    private String imagemUrl;

    @Schema(description = "Preço do produto", example = "5.50")
    private Double preco;

    @Schema(description = "Tempo Preparo em minutos", example = "5")
    private Integer tempoPreparoMinutos;

    @Schema(description = "Categoria do produto", example = "Lanche")
    private String categoria;

}
