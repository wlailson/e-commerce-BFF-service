package io.wlailson.github.e_commerce_BFF_service.api.analytics;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "Registro de uma venda para consulta analítica.")
public record SaleAnalyticsResponseDTO(
        @Schema(description = "Identificador do registro analítico.", example = "sale-123")
        String id,
        @Schema(description = "Identificador do pedido.", example = "101")
        Long orderId,
        @Schema(description = "Identificador do usuário.", example = "42")
        Long userId,
        @Schema(description = "Valor da venda.", example = "149.90")
        BigDecimal amount,
        @Schema(description = "Status da venda.", example = "PAID")
        String status,
        @Schema(description = "Data e hora em que a venda ocorreu.", example = "2026-02-15T10:30:00")
        LocalDateTime occurredAt
) {
}
