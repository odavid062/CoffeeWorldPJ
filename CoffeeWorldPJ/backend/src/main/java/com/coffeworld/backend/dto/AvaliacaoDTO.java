package com.coffeworld.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Schema(description = "DTO que representa a avaliação de um pedido")
public class AvaliacaoDTO {

    @Schema(description = "ID da avaliação", example = "1")
    private Long id;

    @NotNull(message = "Nota é obrigatória")
    @Min(value = 1, message = "Nota mínima é 1")
    @Max(value = 5, message = "Nota máxima é 5")
    @Schema(description = "Nota de 1 a 5", example = "4")
    private Integer nota;

    @Size(max = 1000, message = "Comentário deve ter no máximo 1000 caracteres")
    @Schema(description = "Comentário opcional do cliente", example = "Atendimento rápido e café excelente!")
    private String comentario;
}
