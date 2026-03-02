package com.sastreria.gestiondeprecios.util;

import java.time.LocalDateTime;

public record ErrorResponse(
        String message,
        String errorCode,
        int status,
        LocalDateTime timestamp
) {
}
