package io.wlailson.github.e_commerce_BFF_service.api.order.conversions;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

@Schema(description = "Itens solicitados para criação de um pedido.")
public record OrderRequest(
        @Schema(description = "Itens do pedido; é necessário informar ao menos um.", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotEmpty(message = "Order must contain at least one item")
        Set<@NotNull @Valid OrderItemRequest> items) {
}
