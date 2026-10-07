package io.wlailson.github.e_commerce_BFF_service.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void resourceNotFoundReturnsNotFoundProblemDetail() {
        var problem = handler.handleResourceNotFound(
                new ResourceNotFoundException("Product not found: 25")
        );

        assertEquals(HttpStatus.NOT_FOUND.value(), problem.getStatus());
        assertEquals("Resource not found", problem.getTitle());
    }

    @Test
    void remoteServiceErrorPreservesStatusContentTypeAndBody() {
        var response = handler.handleRemoteServiceException(
                new RemoteServiceException(503, "{\"error\":\"unavailable\"}", "application/json")
        );

        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());
        assertEquals("application/json", response.getHeaders().getContentType().toString());
        assertEquals("{\"error\":\"unavailable\"}", response.getBody());
    }

    @Test
    void frameworkErrorResponseKeepsItsHttpStatus() {
        var response = handler.handleUnexpectedException(
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid query")
        );

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getBody().getStatus());
    }

    @Test
    void unexpectedErrorDoesNotExposeInternalDetails() {
        var response = handler.handleUnexpectedException(
                new IllegalStateException("sensitive internal detail")
        );

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("An unexpected error occurred.", response.getBody().getDetail());
    }
}
