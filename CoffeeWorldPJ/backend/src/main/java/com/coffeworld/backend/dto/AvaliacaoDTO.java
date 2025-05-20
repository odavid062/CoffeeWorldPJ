package com.coffeworld.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Schema(description = "DTO que representa a avaliação de um pedido")
public class AvaliacaoDTO {

    @Schema(description = "ID da avaliação", example = "1")
    private Long id;

    @Schema(description = "Nota de 1 a 5", example = "4")
    private Integer nota;

    @Schema(description = "Comentário opcional do cliente", example = "Atendimento rápido e café excelente!")
    private String comentario;
}
