package io.wlailson.github.e_commerce_BFF_service.api.payment;


import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "Dados do pagamento retornados pelo serviço de pagamentos.")
public record PaymentResponseDTO(
        @Schema(description = "Identificador do pagamento.", example = "501")
        Long id,

        @Schema(description = "Identificador do pedido associado.", example = "101")
        Long orderId,

        @Schema(description = "Identificador do usuário associado.", example = "42")
        Long userId,

        @Schema(description = "Valor do pagamento.", example = "149.90")
        BigDecimal amount,

        @Schema(description = "Estado atual do pagamento.", example = "PENDING")
        PaymentStatus status,

        @Schema(description = "Método de pagamento.", example = "CREDIT_CARD")
        String paymentMethod,

        @Schema(description = "Data e hora de criação.", example = "2026-02-15T10:30:00")
        LocalDateTime createdAt,

        @Schema(description = "Data e hora da última atualização.", example = "2026-02-15T10:35:00")
        LocalDateTime updatedAt) {

}
