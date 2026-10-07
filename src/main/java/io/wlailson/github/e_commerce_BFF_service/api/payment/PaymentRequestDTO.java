package io.wlailson.github.e_commerce_BFF_service.api.payment;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Schema(description = "Dados para iniciar um pagamento de pedido.")
public record PaymentRequestDTO(
        @Schema(description = "Identificador do pedido.", example = "101", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "Order id is required")
        @Positive(message = "Order id must be positive")
        Long orderId,
        @Schema(description = "Valor do pagamento, maior que zero.", example = "49.90", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "Amount is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be positive")
        BigDecimal amount
) {
}
