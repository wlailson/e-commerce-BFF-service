package io.wlailson.github.e_commerce_BFF_service.service;

import io.wlailson.github.e_commerce_BFF_service.api.identity.UserRequestDTO;
import io.wlailson.github.e_commerce_BFF_service.api.identity.LoginRequestDTO;
import io.wlailson.github.e_commerce_BFF_service.api.identity.UserResponseDTO;
import io.wlailson.github.e_commerce_BFF_service.api.identity.UserResponseMinDTO;
import io.wlailson.github.e_commerce_BFF_service.clients.IdentityClient;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class IdentityService {

    private final IdentityClient client;

    public UserResponseDTO getUserById(Long userId) {
        return client.getUserById(userId);
    }

    public Page<UserResponseMinDTO> getAllUsers(Pageable pageable) {
        return client.getAllUsers(pageable);
    }

    public UserResponseDTO getCurrentUser() {
        return client.getCurrentUser();
    }

    public UserResponseDTO postUser(UserRequestDTO request) {
        return client.postUser(request);
    }

    public String login(LoginRequestDTO request) {
        return client.login(request);
    }

    public UserResponseDTO putUser(Long userId, UserRequestDTO request) {
        return client.putUser(userId, request);
    }

    public void deleteUser(Long userId) {
       client.deleteUser(userId);
    }
}
