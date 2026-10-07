package io.wlailson.github.e_commerce_BFF_service.validation;

import io.wlailson.github.e_commerce_BFF_service.api.order.conversions.OrderItemRequest;
import io.wlailson.github.e_commerce_BFF_service.api.order.conversions.OrderRequest;
import io.wlailson.github.e_commerce_BFF_service.api.payment.PaymentRequestDTO;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RequestValidationTest {

    private static Validator validator;
    private static jakarta.validation.ValidatorFactory validatorFactory;

    @BeforeAll
    static void setUpValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterAll
    static void closeValidator() {
        validatorFactory.close();
    }

    @Test
    void orderRequiresItemsWithPositiveProductAndQuantity() {
        OrderRequest invalidOrder = new OrderRequest(Set.of(
                new OrderItemRequest(0L, 0)
        ));
        OrderRequest validOrder = new OrderRequest(Set.of(
                new OrderItemRequest(12L, 2)
        ));

        assertFalse(validator.validate(invalidOrder).isEmpty());
        assertTrue(validator.validate(validOrder).isEmpty());
        assertFalse(validator.validate(new OrderRequest(Set.of())).isEmpty());
    }

    @Test
    void paymentRequiresPositiveOrderIdAndAmount() {
        PaymentRequestDTO invalidPayment = new PaymentRequestDTO(0L, BigDecimal.ZERO);
        PaymentRequestDTO validPayment = new PaymentRequestDTO(12L, new BigDecimal("49.90"));

        assertFalse(validator.validate(invalidPayment).isEmpty());
        assertTrue(validator.validate(validPayment).isEmpty());
    }
}
