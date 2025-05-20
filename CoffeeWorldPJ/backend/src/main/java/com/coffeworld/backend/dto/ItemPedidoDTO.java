package com.coffeworld.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Schema(description = "DTO que representa um item de pedido")
public class ItemPedidoDTO {
    @Schema(description = "ID do item", example = "1")
    private Long id;

    @Schema(description = "ID do produto", example = "10")
    private Long produtoId;

    @Schema(description = "Quantidade escolhida", example = "2")
    private Integer quantidade;

    @Schema(description = "Detalhes do produto")
    private ProdutoDTO produto;  // **Adicionado este campo**
    
}
