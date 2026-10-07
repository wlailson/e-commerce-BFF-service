package io.wlailson.github.e_commerce_BFF_service.api.catalog;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Resumo de um produto do catálogo.")
public record ProductMinDTO(
        @Schema(description = "Identificador do produto.", example = "25")
        Long id,
        @Schema(description = "Nome do produto.", example = "Cafeteira")
        String name,
        @Schema(description = "Preço unitário.", example = "149.90")
        BigDecimal price,
        @Schema(description = "URL ou caminho da imagem do produto.", example = "https://example.com/cafeteira.jpg")
        String imgUrl,
        @Schema(description = "Quantidade disponível em estoque.", example = "12")
        Integer stock) {
}
