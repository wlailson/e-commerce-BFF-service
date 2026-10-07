package io.wlailson.github.e_commerce_BFF_service.clients;

import io.wlailson.github.e_commerce_BFF_service.api.payment.PaymentRequestDTO;
import io.wlailson.github.e_commerce_BFF_service.api.payment.PaymentResponseDTO;
import io.wlailson.github.e_commerce_BFF_service.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        value = "payment-service",
        url = "${services.payment.url}",
        configuration = FeignConfig.class
)
public interface PaymentClient {

    @GetMapping("/{paymentId}")
    PaymentResponseDTO findPaymentById(@PathVariable Long paymentId);

    @GetMapping
    Page<PaymentResponseDTO> findAllPayments(Pageable pageable);

    @PostMapping
    PaymentResponseDTO insertPayment(@RequestBody PaymentRequestDTO request);
}
