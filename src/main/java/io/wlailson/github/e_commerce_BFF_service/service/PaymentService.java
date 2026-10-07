package io.wlailson.github.e_commerce_BFF_service.service;

import io.wlailson.github.e_commerce_BFF_service.api.payment.PaymentRequestDTO;
import io.wlailson.github.e_commerce_BFF_service.api.payment.PaymentResponseDTO;
import io.wlailson.github.e_commerce_BFF_service.clients.PaymentClient;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentClient client;

    public PaymentResponseDTO findPaymentById(Long paymentId) {
        return client.findPaymentById(paymentId);
    }

    public Page<PaymentResponseDTO> findAllPayments(Pageable pageable) {
        return client.findAllPayments(pageable);
    }

    public PaymentResponseDTO insertPayment(PaymentRequestDTO request) {
        return client.insertPayment(request);
    }

}