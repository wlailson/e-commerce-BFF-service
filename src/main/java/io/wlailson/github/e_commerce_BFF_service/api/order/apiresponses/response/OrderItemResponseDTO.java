package io.wlailson.github.e_commerce_BFF_service.api.order.apiresponses.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Item retornado pelo serviço de pedidos.")
public record OrderItemResponseDTO(
        @Schema(description = "Identificador do item do pedido.", example = "1001")
        Long id,
        @Schema(description = "Quantidade comprada.", example = "2")
        Integer quantity,
        @Schema(description = "Preço unitário no momento da compra.", example = "149.90")
        BigDecimal price,
        @Schema(description = "Identificador do produto.", example = "25")
        Long productId) {

}
