package com.kulikov.clothing_store.dto.response;

import java.util.List;

public record ClothingResponseDto (
        Long id,
        String name,
        List<String> sizes,
        List<String> photos,
        List<String> patterns,
        List<MaterialResponseDto> materials
){
}
