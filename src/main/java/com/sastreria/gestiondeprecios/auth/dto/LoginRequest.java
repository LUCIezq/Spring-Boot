package com.sastreria.gestiondeprecios.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(
        @NotBlank(message = "El correo es obligatorio")
        @Email(message = "El email debe tener un formato valido")
        String email,
        @NotBlank(message = "La contraseña es obligatoria")
        //-> Deberiamos crear una validacion personalizada (Longitud/caracteres especiales/min y mayus)
        @Size(min = 8, message = "La contraseña debe tener una longitud minima de 8 caracteres ")
        String password
) {
}
