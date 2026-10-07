package io.wlailson.github.e_commerce_BFF_service.api.order.conversions;

import io.wlailson.github.e_commerce_BFF_service.api.order.apiresponses.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@Schema(description = "Representação completa de um pedido.")
public record OrderDTO(
        @Schema(description = "Identificador do pedido.", example = "101")
        Long id,
        @Schema(description = "Identificador do pagamento associado.", example = "501")
        Long paymentId,
        @Schema(description = "Data e hora de criação do pedido.", example = "2026-02-15T10:30:00Z")
        Instant moment,
        @Schema(description = "Estado atual do pedido.", example = "CREATED")
        OrderStatus status,
        @Schema(description = "Itens incluídos no pedido.")
        Set<OrderItem> items) {

    @Schema(description = "Valor total calculado pela soma dos itens.", example = "299.80")
    public BigDecimal getTotalPrice() {
        return items.stream()
                .map(OrderItem::getTotalItemPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
