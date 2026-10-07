package io.wlailson.github.e_commerce_BFF_service.api.analytics;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Contagem de vendas para um status.")
public record DashboardSalesByStatusDTO(
        @Schema(description = "Status das vendas.", example = "PAID")
        String status,
        @Schema(description = "Quantidade de vendas no status.", example = "125")
        Long total
) {
}
