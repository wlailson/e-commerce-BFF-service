package io.wlailson.github.e_commerce_BFF_service.clients;

import io.wlailson.github.e_commerce_BFF_service.config.FeignConfig;
import io.wlailson.github.e_commerce_BFF_service.api.identity.LoginRequestDTO;
import io.wlailson.github.e_commerce_BFF_service.api.identity.UserRequestDTO;
import io.wlailson.github.e_commerce_BFF_service.api.identity.UserResponseDTO;
import io.wlailson.github.e_commerce_BFF_service.api.identity.UserResponseMinDTO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        value = "identity-service",
        url = "${services.identity.url}",
        configuration = FeignConfig.class
)
public interface IdentityClient {

    @GetMapping("/{userId}")
    ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long userId);

    @GetMapping
    ResponseEntity<Page<UserResponseMinDTO>> getAllUsers(@SpringQueryMap Pageable pageable);

    @GetMapping("/me")
    ResponseEntity<UserResponseDTO> getCurrentUser();

    @PostMapping
    ResponseEntity<UserResponseDTO> postUser(@RequestBody UserRequestDTO request);

    @PostMapping("/login")
    ResponseEntity<String> login(@RequestBody LoginRequestDTO request);

    @PutMapping("/{userId}")
    ResponseEntity<UserResponseDTO> putUser(
            @PathVariable Long userId,
            @RequestBody @Valid UserRequestDTO request);

    @DeleteMapping("/{userId}")
    ResponseEntity<Void> deleteUser(@PathVariable Long userId);
}
