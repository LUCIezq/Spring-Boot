package com.sastreria.gestiondeprecios.util;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Map;

@Builder
public record ErrorResponse(
        String message,
        int status,
        LocalDateTime timestamp,
        String path,
        Map<String, String> errors
) {
}
