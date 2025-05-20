package com.coffeworld.backend.service;

import com.coffeworld.backend.dto.AvaliacaoDTO;
import com.coffeworld.backend.mapper.AvaliacaoMapper;
import com.coffeworld.backend.model.Avaliacao;
import com.coffeworld.backend.model.Pedido;
import com.coffeworld.backend.repository.AvaliacaoRepository;
import com.coffeworld.backend.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private AvaliacaoMapper avaliacaoMapper;

    @Transactional(rollbackFor = Throwable.class)
    public AvaliacaoDTO salvar(Long pedidoId, AvaliacaoDTO dto) {
        Optional<Pedido> pedidoOpt = pedidoRepository.findById(pedidoId);
        if (pedidoOpt.isEmpty()) {
            throw new RuntimeException("Pedido não encontrado");
        }

        Avaliacao avaliacao = avaliacaoMapper.toEntity(dto);
        avaliacao.setPedido(pedidoOpt.get());
        Avaliacao salva = avaliacaoRepository.save(avaliacao);

        return avaliacaoMapper.toDTO(salva);
    }

    @Transactional(readOnly = true)
    public AvaliacaoDTO buscarPorPedido(Long pedidoId) {
        Optional<Avaliacao> avaliacaoOpt = avaliacaoRepository.findByPedidoId(pedidoId);
        return avaliacaoOpt.map(avaliacaoMapper::toDTO).orElse(null);
    }
}

