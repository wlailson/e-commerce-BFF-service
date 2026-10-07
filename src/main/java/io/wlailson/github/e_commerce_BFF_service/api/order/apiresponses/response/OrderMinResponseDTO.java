package io.wlailson.github.e_commerce_BFF_service.api.order.apiresponses.response;


import io.wlailson.github.e_commerce_BFF_service.api.order.apiresponses.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.Instant;

@Schema(description = "Resumo de um pedido.")
public record OrderMinResponseDTO(
        @Schema(description = "Identificador do pedido.", example = "101")
        Long id,
        @Schema(description = "Data e hora de criação do pedido.", example = "2026-02-15T10:30:00Z")
        Instant moment,
        @Schema(description = "Estado atual do pedido.", example = "PAID")
        OrderStatus status,
        @Schema(description = "Valor total do pedido.", example = "299.80")
        BigDecimal total) {

}
