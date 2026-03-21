package com.sastreria.gestiondeprecios.productCategory.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProductCategoryRequest(
        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 3, max = 20, message = "El nombre debe tener una longitud minima de 3 y 20 caracteres.")
        String name,
        @Size(min = 3, max = 100, message = "La descripcion debe tener una longitud minima de 3 y 100 caracteres.")
        String description
) {
}
