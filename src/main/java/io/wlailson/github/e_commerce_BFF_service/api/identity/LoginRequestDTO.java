package io.wlailson.github.e_commerce_BFF_service.api.identity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Credenciais para autenticação.")
public record LoginRequestDTO(
        @Schema(description = "Endereço de e-mail cadastrado.", example = "maria@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid")
        String email,
        @Schema(description = "Senha da conta.", example = "SenhaSegura123", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "Password is required")
        String password
) {
}
