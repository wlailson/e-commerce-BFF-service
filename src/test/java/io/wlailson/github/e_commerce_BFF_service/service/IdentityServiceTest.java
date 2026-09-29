package io.wlailson.github.e_commerce_BFF_service.service;

import io.wlailson.github.e_commerce_BFF_service.api.identity.LoginRequestDTO;
import io.wlailson.github.e_commerce_BFF_service.api.identity.UserRequestDTO;
import io.wlailson.github.e_commerce_BFF_service.api.identity.UserResponseDTO;
import io.wlailson.github.e_commerce_BFF_service.api.identity.UserResponseMinDTO;
import io.wlailson.github.e_commerce_BFF_service.clients.IdentityClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IdentityServiceTest {

    private static final Long USER_ID = 42L;

    @Mock
    private IdentityClient client;

    @InjectMocks
    private IdentityService service;

    @Test
    void getUserByIdDelegatesAndReturnsResponse() {
        ResponseEntity<UserResponseDTO> response = userResponse();
        when(client.getUserById(USER_ID)).thenReturn(response);

        assertThat(service.getUserById(USER_ID)).isSameAs(response);

        verify(client).getUserById(USER_ID);
    }

    @Test
    void getAllUsersDelegatesAndReturnsResponse() {
        PageRequest pageable = PageRequest.of(0, 10);
        ResponseEntity<Page<UserResponseMinDTO>> response =
                ResponseEntity.ok(Page.empty(pageable));
        when(client.getAllUsers(pageable)).thenReturn(response);

        assertThat(service.getAllUsers(pageable)).isSameAs(response);

        verify(client).getAllUsers(pageable);
    }

    @Test
    void getCurrentUserDelegatesAndReturnsResponse() {
        ResponseEntity<UserResponseDTO> response = userResponse();
        when(client.getCurrentUser()).thenReturn(response);

        assertThat(service.getCurrentUser()).isSameAs(response);

        verify(client).getCurrentUser();
    }

    @Test
    void postUserDelegatesAndReturnsResponse() {
        UserRequestDTO request = userRequest();
        ResponseEntity<UserResponseDTO> response = userResponse();
        when(client.postUser(request)).thenReturn(response);

        assertThat(service.postUser(request)).isSameAs(response);

        verify(client).postUser(request);
    }

    @Test
    void loginDelegatesAndReturnsResponse() {
        LoginRequestDTO request = new LoginRequestDTO("user@example.com", "password");
        ResponseEntity<String> response = ResponseEntity.ok("token");
        when(client.login(request)).thenReturn(response);

        assertThat(service.login(request)).isSameAs(response);

        verify(client).login(request);
    }

    @Test
    void putUserDelegatesAndReturnsResponse() {
        UserRequestDTO request = userRequest();
        ResponseEntity<UserResponseDTO> response = userResponse();
        when(client.putUser(USER_ID, request)).thenReturn(response);

        assertThat(service.putUser(USER_ID, request)).isSameAs(response);

        verify(client).putUser(USER_ID, request);
    }

    @Test
    void deleteUserDelegatesAndReturnsResponse() {
        ResponseEntity<Void> response = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        when(client.deleteUser(USER_ID)).thenReturn(response);

        assertThat(service.deleteUser(USER_ID)).isSameAs(response);

        verify(client).deleteUser(USER_ID);
    }

    private static ResponseEntity<UserResponseDTO> userResponse() {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .header("X-Identity-Service", "forwarded")
                .body(new UserResponseDTO(
                        USER_ID,
                        "User",
                        "user@example.com",
                        "123456789",
                        LocalDate.of(1990, 1, 1),
                        List.of("USER")
                ));
    }

    private static UserRequestDTO userRequest() {
        return new UserRequestDTO(
                "User",
                "user@example.com",
                LocalDate.of(1990, 1, 1),
                "123456789",
                "password"
        );
    }
}
