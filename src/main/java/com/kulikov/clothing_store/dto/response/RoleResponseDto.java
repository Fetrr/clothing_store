package com.kulikov.clothing_store.dto.response;

import java.util.List;

public record RoleResponseDto(
        Long id,
        String name,
        String description,
        List<PermissionResponseDto> permissions
) {}
