package io.wlailson.github.e_commerce_BFF_service.service;

import io.wlailson.github.e_commerce_BFF_service.api.analytics.DashboardAnalyticsDTO;
import io.wlailson.github.e_commerce_BFF_service.api.analytics.SaleAnalyticsResponseDTO;
import io.wlailson.github.e_commerce_BFF_service.api.catalog.ProductDTO;
import io.wlailson.github.e_commerce_BFF_service.api.catalog.ProductMinDTO;
import io.wlailson.github.e_commerce_BFF_service.clients.AnalyticsClient;
import io.wlailson.github.e_commerce_BFF_service.clients.CatalogClient;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalyticsService {

    private final AnalyticsClient client;

    public Page<SaleAnalyticsResponseDTO> getSales(Pageable pageable) {
        return client.getSales(pageable);
    }

    public DashboardAnalyticsDTO dashboard() {
        return client.dashboard();
    }
}
