package io.wlailson.github.e_commerce_BFF_service.exceptions;

public class RemoteServiceException extends RuntimeException {

    private final int status;
    private final String body;
    private final String contentType;

    public RemoteServiceException(
            int status,
            String body,
            String contentType
    ) {
        this.status = status;
        this.body = body;
        this.contentType = contentType;
    }

    public int getStatus() {
        return status;
    }

    public String getBody() {
        return body;
    }

    public String getContentType() {
        return contentType;
    }
}
