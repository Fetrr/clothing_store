package com.kulikov.clothing_store.dto.response;

import java.util.List;

public record MaterialResponseDto(
        Long id,
        String name,
        double amountOfMaterial,
        List<String> colours,
        List<String> composition
) {}
