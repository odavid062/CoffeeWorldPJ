package com.coffeworld.backend.mapper;

import com.coffeworld.backend.dto.AvaliacaoDTO;
import com.coffeworld.backend.model.Avaliacao;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AvaliacaoMapper {
    AvaliacaoDTO toDTO(Avaliacao entity);
    Avaliacao toEntity(AvaliacaoDTO dto);
}
