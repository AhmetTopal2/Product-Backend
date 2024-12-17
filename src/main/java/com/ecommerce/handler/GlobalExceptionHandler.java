package com.ecommerce.handler;

import com.ecommerce.exception.ErrorMessage;
import com.ecommerce.exception.GlobalException;
import com.ecommerce.exception.MessageType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler({GlobalException.class})
    public ResponseEntity<ApiError> handleException(GlobalException exception , WebRequest request){
        HttpStatus status = determineHttpStatus(exception.getMessage());
        return ResponseEntity
            .status(status)
            .body(createApiError(request , exception.getMessage()));
    }

    private HttpStatus determineHttpStatus(String message) {
        if (message.contains(MessageType.RESOURCE_NOT_FOUND.getMessage())) {
            return HttpStatus.NOT_FOUND;
        } else if (message.contains(MessageType.VALIDATION_ERROR.getMessage())) {
            return HttpStatus.BAD_REQUEST;
        } else if (message.contains(MessageType.UNAUTHORIZED.getMessage())) {
            return HttpStatus.UNAUTHORIZED;
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    private String getHostName(){
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            throw new GlobalException(new ErrorMessage(MessageType.INTERNAL_SERVER_ERROR, "Host Name Connection Error"));
        }

    }

    public <E> ApiError<E> createApiError(WebRequest request , E message) {
        Exception<E> exception = Exception.<E>builder()
                .createDate(new Date())
                .hostName(getHostName())
                .path(request.getDescription(false).substring(4))
                .message(message)
                .build();

        return ApiError.<E>builder()
                .status(determineHttpStatus(message.toString()).value())
                .exception(exception)
                .build();
    }

}