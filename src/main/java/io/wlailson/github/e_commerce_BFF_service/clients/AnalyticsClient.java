package io.wlailson.github.e_commerce_BFF_service.clients;

import io.wlailson.github.e_commerce_BFF_service.api.analytics.DashboardAnalyticsDTO;
import io.wlailson.github.e_commerce_BFF_service.api.analytics.SaleAnalyticsResponseDTO;
import io.wlailson.github.e_commerce_BFF_service.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        value = "analytics-service",
        url = "${services.analytics.url}",
        configuration = FeignConfig.class
)
public interface AnalyticsClient {

    @GetMapping("/sales")
    Page<SaleAnalyticsResponseDTO> getSales(Pageable pageable);

    @GetMapping("/dashboard")
    DashboardAnalyticsDTO dashboard();
}
