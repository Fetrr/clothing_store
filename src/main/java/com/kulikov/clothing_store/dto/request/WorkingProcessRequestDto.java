package com.kulikov.clothing_store.dto.request;

import java.util.List;

public record WorkingProcessRequestDto (
        Long id,
        String name,
        int duration,
        List<String> instruments
) {
}
