package com.sastreria.gestiondeprecios.auth.dto;

import com.sastreria.gestiondeprecios.users.dto.UserResponse;

public record AuthResponse(
        String token,
        String type,
        UserResponse user
) {
}
