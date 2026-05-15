package com.coffeworld.backend.dto;

import com.coffeworld.backend.enums.StatusPedido;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "DTO que representa um pedido")
@Data
public class PedidoDTO {

    @Schema(description = "ID do pedido", example = "1")
    private Long id;

    @NotBlank(message = "CPF é obrigatório")
    @Pattern(regexp = "\\d{11}", message = "CPF deve conter exatamente 11 dígitos numéricos")
    @Schema(description = "CPF do cliente (somente números)", example = "12345678901")
    private String cpfCliente;

    @Schema(description = "Valor total do pedido", example = "24.90")
    private Double valorTotal;

    @NotBlank(message = "Forma de pagamento é obrigatória")
    @Schema(description = "Forma de pagamento (ex: PIX, CARTAO)", example = "PIX")
    private String formaPagamento;

    @Schema(description = "Data e hora em que o pedido foi feito")
    private LocalDateTime dataHoraPedido;

    @Schema(description = "Previsão de entrega do pedido")
    private LocalDateTime previsaoEntrega;

    @Schema(description = "Status do pedido", example = "PENDENTE")
    private StatusPedido status;

    @Valid
    @NotEmpty(message = "O pedido deve ter ao menos um item")
    @Schema(description = "Lista de itens do pedido")
    private List<ItemPedidoDTO> itens;

    @Schema(description = "Avaliação feita para o pedido")
    private AvaliacaoDTO avaliacao;

    @Size(max = 500, message = "Motivo de cancelamento deve ter no máximo 500 caracteres")
    @Schema(description = "Motivo do cancelamento, se houver")
    private String motivoCancelamento;

    @Size(max = 500, message = "Observação deve ter no máximo 500 caracteres")
    @Schema(description = "Observação do pedido", example = "Preciso de uma colher")
    private String observacao;
}
