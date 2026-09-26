package io.wlailson.github.e_commerce_BFF_service.infra.client.api;

import java.time.LocalDate;
import java.util.List;

public record UserResponseDTO(
        Long id,
        String name,
        String email,
        String phone,
        LocalDate birthDate,
        List<String> roles

) {
}
