package com.coffeworld.backend.dto;

import com.coffeworld.backend.enums.StatusPedido;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "DTO que representa um pedido")
@Data
public class PedidoDTO {
    @Schema(description = "ID do pedido", example = "1")
    private Long id;

    @Schema(description = "CPF do cliente", example = "12345678901")
    private String cpfCliente;

    @Schema(description = "Valor total do pedido", example = "24.90")
    private Double valorTotal;

    @Schema(description = "Forma de pagamento (ex: PIX, CARTAO)", example = "PIX")
    private String formaPagamento;

    @Schema(description = "Data e hora em que o pedido foi feito")
    private LocalDateTime dataHoraPedido;

    @Schema(description = "Previsão de entrega do pedido")
    private LocalDateTime previsaoEntrega;

    @Schema(description = "Status do pedido", example = "PENDENTE")
    private StatusPedido status;

    @Schema(description = "Lista de itens do pedido")
    private List<ItemPedidoDTO> itens;

    @Schema(description = "Avaliação feita para o pedido")
    private AvaliacaoDTO avaliacao;

    @Schema(description = "Motivo do cancelamento, se houver")
    private String motivoCancelamento;
    
    @Schema(description = "Observação do pedido do produto",  example = "Preciso de uma colher")
    private String observacao; 

}
