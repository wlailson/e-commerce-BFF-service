package io.wlailson.github.e_commerce_BFF_service.api.order.apiresponses.request;

import io.wlailson.github.e_commerce_BFF_service.api.order.apiresponses.OrderEvent;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Evento de transição de estado solicitado para um pedido.")
public record OrderUpdateRequest(
        @Schema(description = "Evento a aplicar ao pedido.", example = "PAY", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "Order event is required")
        OrderEvent event) {
}
