package com.sastreria.gestiondeprecios.users.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sastreria.gestiondeprecios.enums.Rol;
import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Builder
public record UserResponse(
        Long id,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime createdAt,
        Rol rol
) {
}
