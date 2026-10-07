package io.wlailson.github.e_commerce_BFF_service.api.catalog;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "Dados completos de um produto do catálogo.")
public record ProductDTO(
        @Schema(description = "Identificador do produto; omitido na criação.", example = "25", accessMode = Schema.AccessMode.READ_ONLY)
        Long id,
        @Schema(description = "Nome do produto.", example = "Cafeteira", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "Campo requerido")
        @Size(min = 3, max = 80, message = "Nome precisa ter de 3 a 80 caracteres")
        String name,
        @Schema(description = "Descrição com pelo menos 10 caracteres.", example = "Cafeteira elétrica de 30 xícaras", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "Campo requerido")
        @Size(min = 10, message = "Descrição precisa ter no mínimo 10 caracteres")
        String description,
        @Schema(description = "Preço unitário, maior que zero.", example = "149.90", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "Preço é obrigatório")
        @Positive(message = "O preço deve ser positivo")
        BigDecimal price,
        @Schema(description = "URL ou caminho da imagem do produto.", example = "https://example.com/cafeteira.jpg")
        String imgUrl,
        @Schema(description = "Quantidade disponível em estoque, zero ou maior.", example = "12", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "Estoque é obrigatório")
        @PositiveOrZero(message = "Estoque não pode ser negativo")
        Integer stock,
        @Schema(description = "Categorias associadas; é necessário informar ao menos uma.", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotEmpty(message = "Deve ter pelo menos uma categoria")
        List<@NotNull @Valid CategoryDTO> categories) {
}