package com.sastreria.gestiondeprecios.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @NotBlank(message = "El name es obligatorio")
        @Size(min = 3, max = 50, message = "El name debe tener entre 3 y 50 caracteres.")
        String name,
        @NotBlank(message = "El surname es obligatorio")
        @Size(min = 3, max = 50, message = "El surname debe tener entre 3 y 50 caracteres.")
        String surname,
        @NotBlank(message = "El email es obligatorio.")
        @Email(message = "El email debe tener un formato valido")
        String email
) {
}
