package io.wlailson.github.e_commerce_BFF_service.controller;

import io.wlailson.github.e_commerce_BFF_service.api.analytics.DashboardAnalyticsDTO;
import io.wlailson.github.e_commerce_BFF_service.api.analytics.SaleAnalyticsResponseDTO;
import io.wlailson.github.e_commerce_BFF_service.service.AnalyticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springdoc.core.annotations.ParameterObject;

@RequestMapping("/api/analytics")
@RestController
@RequiredArgsConstructor
@Tag(name = "Análises", description = "Relatórios e indicadores de vendas.")
@SecurityRequirement(name = "bearerAuth")
public class AnalyticsController {

    private final AnalyticsService service;

    @GetMapping("/sales")
    @Operation(summary = "Consultar vendas", description = "Retorna uma página de registros de vendas para análise.")
    @ApiResponse(responseCode = "200", description = "Página de vendas.")
    public ResponseEntity<Page<SaleAnalyticsResponseDTO>> getSales(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(service.getSales(pageable));
    }

    @GetMapping("/dashboard")
    @Operation(summary = "Consultar painel de vendas", description = "Retorna indicadores agregados de vendas.")
    @ApiResponse(responseCode = "200", description = "Indicadores do painel.")
    public ResponseEntity<DashboardAnalyticsDTO> getDashboard() {
        return ResponseEntity.ok(service.dashboard());
    }
}
