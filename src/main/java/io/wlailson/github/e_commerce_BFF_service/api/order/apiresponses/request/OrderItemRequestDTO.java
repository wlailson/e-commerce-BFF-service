package io.wlailson.github.e_commerce_BFF_service.api.order.apiresponses.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Schema(description = "Item do pedido encaminhado ao serviço de pedidos.")
public record OrderItemRequestDTO(
        @Schema(description = "Identificador do produto.", example = "25", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "Product id is required")
        @Positive(message = "Product id must be positive")
        Long productId,
        @Schema(description = "Quantidade solicitada, maior que zero.", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "Quantity is required")
        @Positive(message = "Quantity must be positive")
        Integer quantity,
        @Schema(description = "Preço unitário do produto.", example = "149.90", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "Price is required")
        @Positive(message = "Price must be positive")
        BigDecimal price
) {
}