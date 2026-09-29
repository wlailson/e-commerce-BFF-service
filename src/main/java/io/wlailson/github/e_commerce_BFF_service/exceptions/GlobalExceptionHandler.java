package io.wlailson.github.e_commerce_BFF_service.exceptions;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RemoteServiceException.class)
    public ResponseEntity<String> handleRemoteServiceException(
            RemoteServiceException ex
    ) {
        return ResponseEntity
                .status(ex.getStatus())
                .contentType(MediaType.parseMediaType(ex.getContentType()))
                .body(ex.getBody());
    }
}
