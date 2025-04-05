package com.kulikov.clothing_store.dto.request;

public record UserRegistrationRequest(
        String username,
        String email,
        String password,
        Long roleId
) {}
