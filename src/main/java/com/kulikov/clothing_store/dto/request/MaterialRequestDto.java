package com.kulikov.clothing_store.dto.request;

import java.util.List;

public record MaterialRequestDto (
    Long id,
    String name,
    double amountOfMaterial,
    List<String> colours,
    List<String> composition) {}
