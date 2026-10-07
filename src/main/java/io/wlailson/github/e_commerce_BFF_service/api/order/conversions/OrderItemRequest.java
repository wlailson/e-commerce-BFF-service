package io.wlailson.github.e_commerce_BFF_service.api.order.conversions;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Schema(description = "Produto e quantidade solicitados para um pedido.")
public record OrderItemRequest(
        @Schema(description = "Identificador do produto.", example = "25", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "Product id is required")
        @Positive(message = "Product id must be positive")
        Long productId,
        @Schema(description = "Quantidade solicitada, maior que zero.", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "Quantity is required")
        @Positive(message = "Quantity must be positive")
        Integer quantity
) {
}