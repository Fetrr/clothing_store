package com.kulikov.clothing_store.dto.response;

import java.util.List;

public record WorkingProcessResponseDto(
        Long id,
        String name,
        int duration,
        List<String> instruments
) {}
