package io.wlailson.github.e_commerce_BFF_service.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import static org.assertj.core.api.Assertions.assertThat;

class FeignConfigTest {

    private final RequestInterceptor interceptor = new FeignConfig().bearerTokenInterceptor();

    @AfterEach
    void clearSecurityContext() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void propagatesBearerTokenFromJwtAuthentication() {
        String token = "test-token";
        Jwt jwt = Jwt.withTokenValue(token)
                .header("alg", "RS256")
                .claim("sub", "user-1")
                .build();
        SecurityContextHolder.getContext()
                .setAuthentication(new JwtAuthenticationToken(jwt));

        RequestTemplate template = requestTemplate();
        interceptor.apply(template);

        assertThat(template.headers().get(HttpHeaders.AUTHORIZATION))
                .containsExactly("Bearer " + token);
    }

    @Test
    void doesNotAddBearerTokenWithoutJwtAuthentication() {
        SecurityContextHolder.clearContext();

        RequestTemplate template = requestTemplate();
        interceptor.apply(template);

        assertThat(template.headers()).doesNotContainKey(HttpHeaders.AUTHORIZATION);
    }

    private static RequestTemplate requestTemplate() {
        RequestTemplate template = new RequestTemplate();
        template.method("GET");
        template.uri("/me", false);
        return template;
    }
}
