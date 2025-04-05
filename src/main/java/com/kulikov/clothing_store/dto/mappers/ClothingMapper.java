package com.kulikov.clothing_store.dto.mappers;

import com.kulikov.clothing_store.dto.request.ClothingRequestDto;
import com.kulikov.clothing_store.dto.response.ClothingResponseDto;
import com.kulikov.clothing_store.models.Clothing;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClothingMapper {
    Clothing toEntity(ClothingRequestDto dto);
    List<Clothing> toEntityList(List<ClothingRequestDto> dtoList);

    ClothingResponseDto toResponse(Clothing clothing);
    List<ClothingResponseDto> toResponseList(List<Clothing> clothingList);
}
