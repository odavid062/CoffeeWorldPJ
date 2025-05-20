package com.coffeworld.backend.mapper;

import com.coffeworld.backend.dto.PedidoDTO;
import com.coffeworld.backend.model.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { ItemPedidoMapper.class, AvaliacaoMapper.class })
public interface PedidoMapper {

    @Mapping(source = "status", target = "status")
    PedidoDTO toDTO(Pedido entity);

    @Mapping(source = "status", target = "status")
    Pedido toEntity(PedidoDTO dto);
}