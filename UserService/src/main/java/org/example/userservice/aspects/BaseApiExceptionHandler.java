package org.example.userservice.aspects;

import org.example.exceptions.api.BaseApiException;
import org.example.models.dto.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class BaseApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BaseApiException.class)
    public ResponseEntity<ExceptionResponse> exceptionHandler(BaseApiException e) {
        return ResponseEntity.status(e.getStatusCode())
                .body(new ExceptionResponse(e.getStatusCode(),
                        e.getLocalizedMessage(),
                        e.getTimestamp())
                );
    }
}
