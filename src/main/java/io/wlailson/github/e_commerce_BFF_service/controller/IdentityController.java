package io.wlailson.github.e_commerce_BFF_service.controller;

import io.wlailson.github.e_commerce_BFF_service.api.identity.LoginRequestDTO;
import io.wlailson.github.e_commerce_BFF_service.api.identity.UserRequestDTO;
import io.wlailson.github.e_commerce_BFF_service.api.identity.UserResponseDTO;
import io.wlailson.github.e_commerce_BFF_service.api.identity.UserResponseMinDTO;
import io.wlailson.github.e_commerce_BFF_service.service.IdentityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/users")
@RestController
@RequiredArgsConstructor
public class IdentityController {

    private final IdentityService service;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long userId) {
        return service.getUserById(userId);
    }

    @GetMapping
    public ResponseEntity<Page<UserResponseMinDTO>> getAllUsers(Pageable pageable) {
        return service.getAllUsers(pageable);
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getCurrentUser() {
        return service.getCurrentUser();
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> postUser(@RequestBody @Valid UserRequestDTO request) {
        return service.postUser(request);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequestDTO request) {

        return service.login(request);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> putUser(
            @PathVariable Long userId,
            @RequestBody @Valid UserRequestDTO request) {
        return service.putUser(userId, request);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        return service.deleteUser(userId);
    }
}
