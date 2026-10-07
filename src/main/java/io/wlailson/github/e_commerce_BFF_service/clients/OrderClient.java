package io.wlailson.github.e_commerce_BFF_service.clients;

import io.wlailson.github.e_commerce_BFF_service.api.catalog.ProductDTO;
import io.wlailson.github.e_commerce_BFF_service.api.order.apiresponses.*;
import io.wlailson.github.e_commerce_BFF_service.api.order.apiresponses.request.OrderRequestDTO;
import io.wlailson.github.e_commerce_BFF_service.api.order.apiresponses.response.OrderMinResponseDTO;
import io.wlailson.github.e_commerce_BFF_service.api.order.apiresponses.response.OrderResponseDTO;
import io.wlailson.github.e_commerce_BFF_service.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        value = "order-service",
        url = "${services.order.url}",
        configuration = FeignConfig.class
)
public interface OrderClient {

    @GetMapping("/{orderId}")
    OrderResponseDTO findOrderById(@PathVariable Long orderId);

    @GetMapping
    Page<OrderMinResponseDTO> findAllOrdersByStatus(
            @RequestParam(defaultValue = "PAID", name = "status")
            OrderStatus status,
            Pageable pageable);

    @PostMapping
    OrderResponseDTO insertOrder(@RequestBody OrderRequestDTO request);
}
