package io.wlailson.github.e_commerce_BFF_service.service;

import io.wlailson.github.e_commerce_BFF_service.api.catalog.ProductDTO;
import io.wlailson.github.e_commerce_BFF_service.api.order.apiresponses.request.OrderItemRequestDTO;
import io.wlailson.github.e_commerce_BFF_service.api.order.apiresponses.request.OrderRequestDTO;
import io.wlailson.github.e_commerce_BFF_service.api.order.apiresponses.response.OrderItemResponseDTO;
import io.wlailson.github.e_commerce_BFF_service.api.order.apiresponses.response.OrderResponseDTO;
import io.wlailson.github.e_commerce_BFF_service.api.order.conversions.OrderDTO;
import io.wlailson.github.e_commerce_BFF_service.api.order.conversions.OrderItem;
import io.wlailson.github.e_commerce_BFF_service.api.order.conversions.OrderItemRequest;
import io.wlailson.github.e_commerce_BFF_service.api.order.conversions.OrderRequest;
import io.wlailson.github.e_commerce_BFF_service.api.order.conversions.Product;
import io.wlailson.github.e_commerce_BFF_service.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public List<Long> productIdsFrom(OrderRequest request) {
        return request.items()
                .stream()
                .map(OrderItemRequest::productId)
                .toList();
    }

    public List<Long> productIdsFrom(OrderResponseDTO order) {
        return order.items()
                .stream()
                .map(OrderItemResponseDTO::productId)
                .toList();
    }

    public OrderRequestDTO toRequestDTO(
            OrderRequest request,
            Map<Long, ProductDTO> productsById
    ) {
        Set<OrderItemRequestDTO> items = request.items()
                .stream()
                .map(item -> new OrderItemRequestDTO(
                        item.productId(),
                        item.quantity(),
                        requireProduct(item.productId(), productsById).price()
                ))
                .collect(Collectors.toSet());

        return new OrderRequestDTO(items);
    }

    public OrderDTO toDto(
            OrderResponseDTO response,
            Map<Long, ProductDTO> productsById
    ) {
        Set<OrderItem> items = response.items()
                .stream()
                .map(item -> new OrderItem(
                        item.id(),
                        item.quantity(),
                        item.price(),
                        new Product(requireProduct(item.productId(), productsById))
                ))
                .collect(Collectors.toSet());

        return new OrderDTO(
                response.id(),
                response.paymentId(),
                response.moment(),
                response.status(),
                items
        );
    }

    private ProductDTO requireProduct(Long productId, Map<Long, ProductDTO> productsById) {
        ProductDTO product = productsById.get(productId);
        if (product == null) {
            throw new ResourceNotFoundException("Product not found: " + productId);
        }
        return product;
    }
}
