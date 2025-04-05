package com.kulikov.clothing_store.dto.response;

import java.util.List;

public record UserResponseDto (
        Long id,
        String username,
        String email,
        String profilePhotoUrl,
        RoleResponseDto role,
        List<ClothingResponseDto> clothingItems
) {
}
