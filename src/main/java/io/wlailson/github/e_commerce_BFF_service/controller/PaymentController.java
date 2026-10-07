package io.wlailson.github.e_commerce_BFF_service.controller;

import io.wlailson.github.e_commerce_BFF_service.api.payment.PaymentRequestDTO;
import io.wlailson.github.e_commerce_BFF_service.api.payment.PaymentResponseDTO;
import io.wlailson.github.e_commerce_BFF_service.service.PaymentService;
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
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/payment")
@RestController
@RequiredArgsConstructor
@Tag(name = "Pagamentos", description = "Consulta e solicitação de pagamentos.")
@SecurityRequirement(name = "bearerAuth")
public class PaymentController {

    private final PaymentService service;

    @GetMapping("/{paymentId}")
    @Operation(summary = "Consultar pagamento", description = "Busca um pagamento pelo identificador.")
    @ApiResponse(responseCode = "200", description = "Pagamento encontrado.")
    public ResponseEntity<PaymentResponseDTO> findPaymentById(
            @Parameter(description = "Identificador do pagamento.", example = "501")
            @PathVariable Long paymentId) {
        return ResponseEntity.ok(service.findPaymentById(paymentId));
    }

    @GetMapping
    @Operation(summary = "Listar pagamentos", description = "Retorna uma página de pagamentos.")
    @ApiResponse(responseCode = "200", description = "Página de pagamentos.")
    public ResponseEntity<Page<PaymentResponseDTO>> findAllPayments(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(service.findAllPayments(pageable));
    }

    @PostMapping
    @Operation(summary = "Solicitar pagamento", description = "Inicia um pagamento para um pedido.")
    @ApiResponse(responseCode = "200", description = "Pagamento solicitado.")
    public ResponseEntity<PaymentResponseDTO> insertPayment(@Valid @RequestBody PaymentRequestDTO request) {
        return ResponseEntity.ok(service.insertPayment(request));
    }
}
