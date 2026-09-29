package io.wlailson.github.e_commerce_BFF_service.api.catalog;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CategoryDTO(
        @NotNull(message = "Id da categoria é obrigatório")
        @Positive(message = "Id da categoria deve ser positivo")
        Long id,
        String name) {
}
