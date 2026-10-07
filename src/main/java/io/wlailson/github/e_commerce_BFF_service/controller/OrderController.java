package io.wlailson.github.e_commerce_BFF_service.controller;

import io.wlailson.github.e_commerce_BFF_service.api.order.apiresponses.OrderStatus;
import io.wlailson.github.e_commerce_BFF_service.api.order.apiresponses.response.OrderMinResponseDTO;
import io.wlailson.github.e_commerce_BFF_service.api.order.conversions.OrderDTO;
import io.wlailson.github.e_commerce_BFF_service.api.order.conversions.OrderRequest;
import io.wlailson.github.e_commerce_BFF_service.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/orders")
@RestController
@RequiredArgsConstructor
@Tag(name = "Pedidos", description = "Consulta e criação de pedidos.")
@SecurityRequirement(name = "bearerAuth")
public class OrderController {

    private final OrderService service;

    @GetMapping("/{orderId}")
    @Operation(summary = "Consultar pedido", description = "Busca os dados completos de um pedido pelo identificador.")
    @ApiResponse(responseCode = "200", description = "Pedido encontrado.")
    public ResponseEntity<OrderDTO> findOrderById(
            @Parameter(description = "Identificador do pedido.", example = "101")
            @PathVariable Long orderId) {
        return ResponseEntity.ok(service.findOrderById(orderId));
    }

    @GetMapping
    @Operation(summary = "Listar pedidos", description = "Retorna uma página de pedidos, opcionalmente filtrada por status.")
    @ApiResponse(responseCode = "200", description = "Página de pedidos.")
    public ResponseEntity<Page<OrderMinResponseDTO>> findAllOrders(
            @Parameter(description = "Status pelo qual filtrar os pedidos.", example = "PAID")
            @RequestParam(required = false)
            OrderStatus status,
            @ParameterObject Pageable pageable
    ) {
        return ResponseEntity.ok(service.findAllOrders(status, pageable));
    }

    @PostMapping
    @Operation(summary = "Criar pedido", description = "Cria um pedido com os produtos e quantidades informados.")
    @ApiResponse(responseCode = "200", description = "Pedido criado.")
    public ResponseEntity<OrderDTO> insertOrder(@Valid @RequestBody OrderRequest request) {
        return ResponseEntity.ok(service.insertOrder(request));
    }
}
