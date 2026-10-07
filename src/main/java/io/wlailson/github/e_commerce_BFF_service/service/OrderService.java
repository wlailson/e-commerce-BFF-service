package io.wlailson.github.e_commerce_BFF_service.service;

import io.wlailson.github.e_commerce_BFF_service.api.catalog.ProductDTO;
import io.wlailson.github.e_commerce_BFF_service.api.order.apiresponses.OrderStatus;
import io.wlailson.github.e_commerce_BFF_service.api.order.apiresponses.request.OrderRequestDTO;
import io.wlailson.github.e_commerce_BFF_service.api.order.apiresponses.response.OrderMinResponseDTO;
import io.wlailson.github.e_commerce_BFF_service.api.order.apiresponses.response.OrderResponseDTO;
import io.wlailson.github.e_commerce_BFF_service.api.order.conversions.OrderDTO;
import io.wlailson.github.e_commerce_BFF_service.api.order.conversions.OrderRequest;
import io.wlailson.github.e_commerce_BFF_service.api.payment.PaymentRequestDTO;
import io.wlailson.github.e_commerce_BFF_service.clients.OrderClient;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderClient orderClient;
    private final CatalogService catalogService;
    private final PaymentService paymentService;
    private final OrderMapper orderMapper;

    public OrderDTO findOrderById(Long orderId) {
        OrderResponseDTO order = orderClient.findOrderById(orderId);
        Map<Long, ProductDTO> productsById =
                findProductsByIds(orderMapper.productIdsFrom(order));
        return orderMapper.toDto(order, productsById);
    }

    public Page<OrderMinResponseDTO> findAllOrders(OrderStatus status, Pageable pageable) {
        return orderClient.findAllOrdersByStatus(status, pageable);
    }

    public OrderDTO insertOrder(OrderRequest request) {
        Map<Long, ProductDTO> productsById =
                findProductsByIds(orderMapper.productIdsFrom(request));
        OrderRequestDTO orderRequest = orderMapper.toRequestDTO(request, productsById);

        OrderResponseDTO order = orderClient.insertOrder(orderRequest);

        OrderDTO response = orderMapper.toDto(order, productsById);

        paymentService.insertPayment(new PaymentRequestDTO(response.id(), response.getTotalPrice()));

        return response;
    }

    private Map<Long, ProductDTO> findProductsByIds(List<Long> productIds) {
        return catalogService.findAllByIds(productIds)
                .stream()
                .collect(Collectors.toMap(ProductDTO::id, product -> product));
    }
}
