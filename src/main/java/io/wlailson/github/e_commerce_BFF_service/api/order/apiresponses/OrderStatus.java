package io.wlailson.github.e_commerce_BFF_service.api.order.apiresponses;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Estados possíveis de um pedido.")
public enum OrderStatus {
    CREATED,
    PAID,
    SHIPPED,
    DELIVERED,
    CANCELLED
}