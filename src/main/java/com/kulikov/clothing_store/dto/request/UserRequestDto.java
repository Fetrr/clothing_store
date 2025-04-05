package com.kulikov.clothing_store.dto.request;

import java.util.List;

public record UserRequestDto (
        Long id,
        String username,
        String email,
        String password,
        String profilePhotoUrl,
        RoleRequestDto role,
        List<ClothingRequestDto> clothingItems
) {
}
