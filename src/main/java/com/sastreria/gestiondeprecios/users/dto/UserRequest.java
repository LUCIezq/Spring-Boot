package com.sastreria.gestiondeprecios.users.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @NotBlank(message = "El nombre del cliente no puede estar vacio")
        @Size(min = 3, max = 100, message = "El nombre debe tener una longitud minima de 3 caracteres y un maximo de 100.")
        String nombre
) {
}
