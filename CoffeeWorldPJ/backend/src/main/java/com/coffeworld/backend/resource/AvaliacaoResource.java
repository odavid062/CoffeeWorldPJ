package com.coffeworld.backend.resource;

import com.coffeworld.backend.dto.AvaliacaoDTO;
import com.coffeworld.backend.service.AvaliacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/avaliacoes")
@Tag(name = "Avaliações", description = "Gerenciamento de avaliações da cafeteria")
@CrossOrigin(origins = "*")
public class AvaliacaoResource {
    @Autowired
    private AvaliacaoService avaliacaoService;

    @Operation(summary = "Criar avaliação para um pedido")
    @PostMapping("/pedido/{pedidoId}")
    public AvaliacaoDTO avaliarPedido(@PathVariable(name = "pedidoId") Long pedidoId, @RequestBody AvaliacaoDTO dto) {
        return avaliacaoService.salvar(pedidoId, dto);
    }

    @Operation(summary = "Buscar avaliação por ID")
    @GetMapping("/pedido/{pedidoId}")
    public AvaliacaoDTO buscarAvaliacao(@PathVariable(name = "pedidoId") Long pedidoId) {
        return avaliacaoService.buscarPorPedido(pedidoId);
    }
}
