package com.kulikov.clothing_store.dto.mappers;

import com.kulikov.clothing_store.dto.response.PermissionResponseDto;
import com.kulikov.clothing_store.models.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PermissonMapper {

    PermissionResponseDto toResponse(Permission permission);
    List<PermissionResponseDto> toResponseList(List<Permission> permissionList);
}
