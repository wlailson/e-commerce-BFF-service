package io.wlailson.github.e_commerce_BFF_service.api.identity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Schema(description = "Dados necessários para criar ou atualizar um usuário.")
public record UserRequestDTO(
        @Schema(description = "Nome completo do usuário.", example = "Maria Silva", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "Name is required")
        @Size(max = 120, message = "Name must have at most 120 characters")
        String name,
        @Schema(description = "Endereço de e-mail.", example = "maria@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid")
        String email,
        @Schema(description = "Data de nascimento, anterior à data atual.", example = "1990-05-20", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "BirthDate Not null")
        @Past(message = "The date must be in the past")
        LocalDate birthDate,
        @Schema(description = "Telefone para contato.", example = "+5511999999999", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "Phone is required")
        @Size(max = 30, message = "Phone must have at most 30 characters")
        String phone,
        @Schema(description = "Senha da conta (mínimo de 8 caracteres).", example = "SenhaSegura123", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "Password is required")
        @Size(min = 8, max = 128, message = "Password must have between 8 and 128 characters")
        String password
) {
}