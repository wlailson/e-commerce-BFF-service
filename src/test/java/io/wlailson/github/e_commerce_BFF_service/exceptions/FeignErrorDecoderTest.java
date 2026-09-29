package io.wlailson.github.e_commerce_BFF_service.exceptions;

import feign.Response;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class FeignErrorDecoderTest {

    private final FeignErrorDecoder decoder = new FeignErrorDecoder();

    @Test
    void preservesDownstreamStatusAndBody() throws Exception {
        String responseBody = "{\"error\":\"invalid token\"}";
        Response response = Response.builder()
                .status(401)
                .reason("Unauthorized")
                .request(feign.Request.create(
                        feign.Request.HttpMethod.GET,
                        "http://identity/users/me",
                        Map.of(),
                        null,
                        StandardCharsets.UTF_8,
                        null
                ))
                .headers(Map.of("Content-Type", (Collection<String>) java.util.List.of("application/json")))
                .body(responseBody, StandardCharsets.UTF_8)
                .build();

        Exception exception = decoder.decode("IdentityClient#getCurrentUser()", response);

        assertThat(exception).isInstanceOf(RemoteServiceException.class);
        RemoteServiceException remoteException = (RemoteServiceException) exception;
        assertThat(remoteException.getStatus()).isEqualTo(401);
        assertThat(remoteException.getBody()).isEqualTo(responseBody);
        assertThat(remoteException.getContentType()).isEqualTo("application/json");
    }
}
