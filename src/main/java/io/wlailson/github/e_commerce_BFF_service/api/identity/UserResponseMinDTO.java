package io.wlailson.github.e_commerce_BFF_service.api.identity;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resumo de um usuário.")
public record UserResponseMinDTO(
        @Schema(description = "Identificador do usuário.", example = "42")
        Long id,
        @Schema(description = "Nome completo.", example = "Maria Silva")
        String name,
        @Schema(description = "Endereço de e-mail.", example = "maria@example.com")
        String email
) {
}
