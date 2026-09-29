package io.wlailson.github.e_commerce_BFF_service.controller;

import io.wlailson.github.e_commerce_BFF_service.api.identity.LoginRequestDTO;
import io.wlailson.github.e_commerce_BFF_service.config.SecurityConfig;
import io.wlailson.github.e_commerce_BFF_service.service.IdentityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        value = IdentityController.class,
        properties = {
                "services.identity.url=http://localhost",
                "services.catalog.url=http://localhost"
        }
)
@Import(SecurityConfig.class)
class IdentityControllerSecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IdentityService identityService;

    @MockitoBean
    private JwtDecoder jwtDecoder;

    @Test
    void loginIsPublic() throws Exception {
        when(identityService.login(any(LoginRequestDTO.class)))
                .thenReturn(ResponseEntity.ok("token"));

        mockMvc.perform(post("/api/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"email":"user@example.com","password":"password"}
                                """))
                .andExpect(status().isOk());
    }

    @Test
    void otherUserEndpointsRequireAuthentication() throws Exception {
        mockMvc.perform(get("/api/users/me"))
                .andExpect(status().isUnauthorized());
    }
}
