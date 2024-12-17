package com.ecommerce.exception;

import org.springframework.http.HttpStatus;

public enum MessageType {
    // 404 errors
    RESOURCE_NOT_FOUND("Requested resource not found", HttpStatus.NOT_FOUND),
    
    // 400 errors
    VALIDATION_ERROR("Validation failed", HttpStatus.BAD_REQUEST),
    BAD_REQUEST("Bad request", HttpStatus.BAD_REQUEST),
    
    // 401/403 errors
    UNAUTHORIZED("Unauthorized access", HttpStatus.UNAUTHORIZED),
    FORBIDDEN("Access forbidden", HttpStatus.FORBIDDEN),
    
    // 500 errors
    INTERNAL_SERVER_ERROR("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String message;
    private final HttpStatus status;

    MessageType(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
