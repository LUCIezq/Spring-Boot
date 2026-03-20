package com.sastreria.gestiondeprecios.productTypes.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProductTypeRequest(
        @NotBlank(message = "El name es obligatorio")
        @Size(min = 3, max = 20, message = "El name debe tener una longitud minima de 3 y 20 caracteres.")
        String name,
        @NotBlank(message = "La descripcion es obligatoria")
        @Size(min = 3, max = 200, message = "La descripcion debe tener una longitud minima de 3 y 200 caracteres.")
        String description
) {
}
