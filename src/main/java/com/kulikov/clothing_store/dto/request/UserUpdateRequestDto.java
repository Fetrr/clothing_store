package com.kulikov.clothing_store.dto.request;

import org.springframework.web.multipart.MultipartFile;

public record UserUpdateRequestDto(
        String username,
        String description,
        MultipartFile profilePhoto
) {}
