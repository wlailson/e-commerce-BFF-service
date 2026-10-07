package io.wlailson.github.e_commerce_BFF_service.api.order.conversions;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Item incluído em um pedido.")
public record OrderItem(
        @Schema(description = "Identificador do item do pedido.", example = "1001")
        Long id,
        @Schema(description = "Quantidade comprada.", example = "2")
        Integer quantity,
        @Schema(description = "Preço unitário no momento da compra.", example = "149.90")
        BigDecimal price,
        @Schema(description = "Produto associado ao item.")
        Product product
) {
    @Schema(description = "Valor total deste item.", example = "299.80")
    public BigDecimal getTotalItemPrice() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }
}