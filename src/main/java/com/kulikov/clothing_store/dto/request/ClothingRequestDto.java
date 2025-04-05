package com.kulikov.clothing_store.dto.request;

import java.util.List;

public record ClothingRequestDto(
    Long id,
    String name,
    List<String> sizes,
    List<String> photos,
    List<String> patterns) {

}
