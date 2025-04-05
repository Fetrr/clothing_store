package com.kulikov.clothing_store.dto.mappers;

import com.kulikov.clothing_store.dto.request.MaterialRequestDto;
import com.kulikov.clothing_store.dto.response.MaterialResponseDto;
import com.kulikov.clothing_store.models.Material;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MaterialMapper {
    Material toEntity(MaterialRequestDto dto);
    List<Material> toEntityList(List<MaterialRequestDto> dtoList);

    MaterialResponseDto toResponse(Material material);
    List<MaterialResponseDto> toResponseList(List<Material> materialList);
}
