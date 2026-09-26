package io.wlailson.github.e_commerce_BFF_service.service;

import io.wlailson.github.e_commerce_BFF_service.infra.client.api.UserRequestDTO;
import io.wlailson.github.e_commerce_BFF_service.infra.client.api.UserResponseDTO;
import io.wlailson.github.e_commerce_BFF_service.infra.client.userclient.IdentityClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Service
public class IdentityService {

    private final IdentityClient client;

    public UserResponseDTO postUser(@RequestBody UserRequestDTO request) {
        return client.postUser(request);
    }
}
