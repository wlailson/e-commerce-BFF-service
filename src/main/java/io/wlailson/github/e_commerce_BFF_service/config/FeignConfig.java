package io.wlailson.github.e_commerce_BFF_service.config;

import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import io.wlailson.github.e_commerce_BFF_service.exceptions.FeignErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

@Slf4j
@Configuration
public class FeignConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignErrorDecoder();
    }

    @Bean
    public RequestInterceptor bearerTokenInterceptor() {
        return requestTemplate -> {

            Authentication authentication =
                    SecurityContextHolder.getContext().getAuthentication();

            if (authentication instanceof JwtAuthenticationToken jwtAuthenticationToken) {
                String token = jwtAuthenticationToken.getToken().getTokenValue();

                log.info("Feign request {} {} - JWT propagated: true",
                        requestTemplate.method(),
                        requestTemplate.path());

                requestTemplate.header(
                        HttpHeaders.AUTHORIZATION,
                        "Bearer " + token
                );
            } else {
                log.info("Feign request {} {} - JWT propagated: false",
                        requestTemplate.method(),
                        requestTemplate.path());
            }
        };
    }
}
