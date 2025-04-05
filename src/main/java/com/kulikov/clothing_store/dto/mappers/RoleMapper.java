package com.kulikov.clothing_store.dto.mappers;

import com.kulikov.clothing_store.dto.request.RoleRequestDto;
import com.kulikov.clothing_store.dto.response.RoleResponseDto;
import com.kulikov.clothing_store.models.Role;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleMapper {
    Role toEntity(RoleRequestDto dto);
    List<Role> toEntityList(List<RoleRequestDto> dtoList);

    RoleResponseDto toResponse(Role role);
    List<RoleResponseDto> toResponseList(List<Role> roleList);
}
