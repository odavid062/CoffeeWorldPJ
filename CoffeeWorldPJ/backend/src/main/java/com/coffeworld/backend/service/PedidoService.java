package com.coffeworld.backend.service;

import com.coffeworld.backend.dto.PedidoDTO;
import com.coffeworld.backend.enums.StatusPedido;
import com.coffeworld.backend.mapper.PedidoMapper;
import com.coffeworld.backend.model.ItemPedido;
import com.coffeworld.backend.model.Pedido;
import com.coffeworld.backend.model.Produto;
import com.coffeworld.backend.repository.PedidoRepository;
import com.coffeworld.backend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PedidoMapper pedidoMapper;

    @Transactional(readOnly = true)
    public List<PedidoDTO> listarTodos() {
        return pedidoRepository.findAll().stream()
                .map(pedidoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PedidoDTO buscarPorId(Long id) {
        return pedidoRepository.findById(id)
                .map(pedidoMapper::toDTO)
                .orElse(null);
    }

    @Transactional(rollbackFor = Throwable.class)
    public PedidoDTO salvar(PedidoDTO pedidoDTO) {
        Pedido pedido = pedidoMapper.toEntity(pedidoDTO);
        pedido.setDataHoraPedido(LocalDateTime.now());

        double valorTotal = 0.0;
        int tempoEstimadoTotal = 0;

        List<ItemPedido> itens = pedidoDTO.getItens().stream().map(itemDTO -> {
            Produto produto = produtoRepository.findById(itemDTO.getProdutoId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + itemDTO.getProdutoId()));

            ItemPedido item = new ItemPedido();
            item.setProduto(produto);
            item.setPedido(pedido);
            item.setQuantidade(itemDTO.getQuantidade());
            return item;
        }).collect(Collectors.toList());

        for (ItemPedido item : itens) {
            valorTotal += item.getProduto().getPreco() * item.getQuantidade();
            tempoEstimadoTotal += item.getProduto().getTempoPreparoMinutos() * item.getQuantidade();
        }

        pedido.setItens(itens);
        pedido.setValorTotal(valorTotal);
        pedido.setPrevisaoEntrega(LocalDateTime.now().plusMinutes(tempoEstimadoTotal));

        // Se não houver status, definir como PENDENTE
        if (pedido.getStatus() == null) {
            pedido.setStatus(StatusPedido.PENDENTE);
        }

        // Adicionar observação, se presente
        if (pedidoDTO.getObservacao() != null && !pedidoDTO.getObservacao().isEmpty()) {
            pedido.setObservacao(pedidoDTO.getObservacao());
        }

        Pedido salvo = pedidoRepository.save(pedido);
        return pedidoMapper.toDTO(salvo);
    }

    @Transactional(rollbackFor = Throwable.class)
    public PedidoDTO atualizarStatus(Long id, PedidoDTO pedidoDTO) {
        Optional<Pedido> optional = pedidoRepository.findById(id);
        if (optional.isPresent()) {
            Pedido pedido = optional.get();
            pedido.setStatus(pedidoDTO.getStatus());
            pedido.setMotivoCancelamento(pedidoDTO.getMotivoCancelamento());

            // Atualizar observação se presente no DTO
            if (pedidoDTO.getObservacao() != null && !pedidoDTO.getObservacao().isEmpty()) {
                pedido.setObservacao(pedidoDTO.getObservacao());
            }

            return pedidoMapper.toDTO(pedidoRepository.save(pedido));
        }
        return null;
    }

    @Transactional(readOnly = true)
    public List<PedidoDTO> listarPorStatus(StatusPedido status) {
        List<Pedido> pedidos = pedidoRepository.findByStatus(status);
        return pedidos.stream()
                .map(pedidoMapper::toDTO)
                .toList();
    }

    @Transactional(rollbackFor = Throwable.class)
    public PedidoDTO atualizarStatus(Long pedidoId, StatusPedido novoStatus) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        pedido.setStatus(novoStatus);
        Pedido atualizado = pedidoRepository.save(pedido);
        return pedidoMapper.toDTO(atualizado);
    }

    @Transactional(rollbackFor = Throwable.class)
    public void deletar(Long id) {
        pedidoRepository.deleteById(id);
    }
}
