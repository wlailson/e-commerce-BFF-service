package io.wlailson.github.e_commerce_BFF_service.infra.client.userclient;

import io.wlailson.github.e_commerce_BFF_service.infra.client.api.UserRequestDTO;
import io.wlailson.github.e_commerce_BFF_service.infra.client.api.UserResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "identity-service", url = "localhost:8081")
public interface IdentityClient {

    @PostMapping("/users")
    UserResponseDTO postUser(@RequestBody UserRequestDTO request);
}
