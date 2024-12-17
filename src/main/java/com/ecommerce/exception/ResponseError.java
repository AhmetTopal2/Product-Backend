package com.ecommerce.exception;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class ResponseError {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    
    public static ResponseError of(ErrorMessage errorMessage, int status, String path) {
        return ResponseError.builder()
                .timestamp(LocalDateTime.now())
                .status(status)
                .error(errorMessage.getMessageType().name())
                .message(errorMessage.prepareErrorMessage())
                .path(path)
                .build();
    }
} 