package com.coffeworld.backend.mapper;

import com.coffeworld.backend.dto.ItemPedidoDTO;
import com.coffeworld.backend.model.ItemPedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemPedidoMapper {

    @Mapping(source = "produto.id", target = "produtoId")
    ItemPedidoDTO toDTO(ItemPedido entity);
}
