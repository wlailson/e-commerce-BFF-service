package io.wlailson.github.e_commerce_BFF_service.api.order.apiresponses;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Eventos permitidos para a transição de um pedido.")
public enum OrderEvent {
    CREATE,
    PAY,
    SHIP,
    DELIVER,
    CANCEL
}
