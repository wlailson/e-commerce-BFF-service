package io.wlailson.github.e_commerce_BFF_service.api.identity;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.List;

@Schema(description = "Dados públicos completos de um usuário.")
public record UserResponseDTO(
        @Schema(description = "Identificador do usuário.", example = "42")
        Long id,
        @Schema(description = "Nome completo.", example = "Maria Silva")
        String name,
        @Schema(description = "Endereço de e-mail.", example = "maria@example.com")
        String email,
        @Schema(description = "Telefone para contato.", example = "+5511999999999")
        String phone,
        @Schema(description = "Data de nascimento.", example = "1990-05-20")
        LocalDate birthDate,
        @Schema(description = "Papéis e permissões atribuídos.")
        List<String> roles

) {
}
