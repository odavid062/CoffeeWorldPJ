package com.coffeworld.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Schema(description = "DTO que representa um produto do cardápio")
public class ProdutoDTO {

    @Schema(description = "ID do produto", example = "1")
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 255, message = "Nome deve ter no máximo 255 caracteres")
    @Schema(description = "Nome do produto", example = "Café Expresso")
    private String nome;

    @Size(max = 1000, message = "Descrição deve ter no máximo 1000 caracteres")
    @Schema(description = "Descrição do produto", example = "Café curto, forte e encorpado")
    private String descricao;

    @Size(max = 500, message = "URL da imagem deve ter no máximo 500 caracteres")
    @Schema(description = "URL da imagem do produto", example = "https://meusite.com/imagens/cafe.jpg")
    private String imagemUrl;

    @NotNull(message = "Preço é obrigatório")
    @Positive(message = "Preço deve ser maior que zero")
    @Schema(description = "Preço do produto", example = "5.50")
    private Double preco;

    @Positive(message = "Tempo de preparo deve ser maior que zero")
    @Schema(description = "Tempo de preparo em minutos", example = "5")
    private Integer tempoPreparoMinutos;

    @NotBlank(message = "Categoria é obrigatória")
    @Schema(description = "Categoria do produto", example = "bebida-quente")
    private String categoria;
}
