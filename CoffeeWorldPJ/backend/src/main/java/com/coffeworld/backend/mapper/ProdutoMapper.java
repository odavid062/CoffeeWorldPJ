package com.coffeworld.backend.mapper;

import com.coffeworld.backend.dto.ProdutoDTO;
import com.coffeworld.backend.model.Produto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {
    ProdutoDTO toDTO(Produto entity);
    Produto toEntity(ProdutoDTO dto);
}
