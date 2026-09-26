package io.wlailson.github.e_commerce_BFF_service.controller;

import io.wlailson.github.e_commerce_BFF_service.infra.client.api.UserRequestDTO;
import io.wlailson.github.e_commerce_BFF_service.infra.client.api.UserResponseDTO;
import io.wlailson.github.e_commerce_BFF_service.service.IdentityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class IdentityController {

    private final IdentityService service;

    @PostMapping
    public ResponseEntity<UserResponseDTO> postUser(@RequestBody UserRequestDTO request) {
        return ResponseEntity.ok(service.postUser(request));
    }
}
