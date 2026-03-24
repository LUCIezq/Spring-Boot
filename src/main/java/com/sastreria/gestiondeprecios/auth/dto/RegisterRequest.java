package com.sastreria.gestiondeprecios.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
        String name,
        @NotBlank(message = "El apellido es obligatorio")
        @Size(min = 3, max = 50, message = "El apellido debe tener entre 3 y 50 caracteres")
        String surname,
        @NotBlank(message = "El correo es obligatorio")
        @Email(message = "El email debe tener un formato valido")
        String email,
        @NotBlank(message = "La contrasena es obligatoria")
        @Size(min = 8, message = "La contrasena debe tener al menos 8 caracteres")
        String password
) {
}
