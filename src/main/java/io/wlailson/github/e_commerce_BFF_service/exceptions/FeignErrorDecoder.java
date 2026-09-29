package io.wlailson.github.e_commerce_BFF_service.exceptions;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Set;

@Slf4j
public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        log.warn("Downstream request {} returned HTTP {}",
                methodKey,
                response.status());

        try {
            String body = response.body() != null
                    ? Util.toString(response.body().asReader())
                    : "";

            String contentType = response.headers()
                    .getOrDefault("Content-Type", Set.of("application/json"))
                    .stream()
                    .findFirst()
                    .orElse("application/json");

            return new RemoteServiceException(
                    response.status(),
                    body,
                    contentType
            );

        } catch (IOException e) {
            return new RuntimeException(e);
        }
    }
}