package io.wlailson.github.e_commerce_BFF_service.api.payment;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Estados possíveis de um pagamento.")
public enum PaymentStatus {
    PENDING,
    APPROVED,
    REFUSED,
    CANCELLED,
    REFUNDED
}
