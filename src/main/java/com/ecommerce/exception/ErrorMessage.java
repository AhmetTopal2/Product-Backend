package com.ecommerce.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
    private MessageType messageType;
    private String ofString;
    
    public String prepareErrorMessage() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(messageType != null ? messageType.getMessage() : "Unknown error");
        if (ofString != null && !ofString.trim().isEmpty()) {
            stringBuilder.append(": ").append(ofString);
        }
        return stringBuilder.toString();
    }
}
