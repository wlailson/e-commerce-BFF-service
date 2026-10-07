package io.wlailson.github.e_commerce_BFF_service.api.catalog;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(description = "Categoria associada a um produto.")
public record CategoryDTO(
        @Schema(description = "Identificador da categoria.", example = "3", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "Id da categoria é obrigatório")
        @Positive(message = "Id da categoria deve ser positivo")
        Long id,
        @Schema(description = "Nome da categoria.", example = "Eletrodomésticos", accessMode = Schema.AccessMode.READ_ONLY)
        String name) {
}
