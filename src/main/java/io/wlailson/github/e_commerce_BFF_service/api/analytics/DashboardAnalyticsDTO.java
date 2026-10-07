package io.wlailson.github.e_commerce_BFF_service.api.analytics;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "Indicadores consolidados para o painel de vendas.")
public record DashboardAnalyticsDTO(
        @Schema(description = "Quantidade total de vendas.", example = "250")
        Long totalSales,
        @Schema(description = "Quantidade de vendas com valor calculado.", example = "245")
        Long salesWithAmount,
        @Schema(description = "Receita total.", example = "28450.75")
        BigDecimal totalRevenue,
        @Schema(description = "Valor médio por venda.", example = "116.12")
        BigDecimal averageTicket,
        @Schema(description = "Quantidade de vendas agrupadas por status.")
        List<DashboardSalesByStatusDTO> salesByStatus
) {
}
