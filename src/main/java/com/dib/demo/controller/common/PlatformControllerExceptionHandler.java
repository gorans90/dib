package com.dib.demo.controller.common;

import com.dib.demo.exception.NotFoundException;
import com.dib.demo.util.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Exception handler that maps exceptions to HTTP error status codes 400+ or 500
 */

@Slf4j
@RestControllerAdvice
public class PlatformControllerExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(NotFoundException e) {
        log.info("Resource not found, message: {}", e.getMessage());
        return ErrorResponse.builder()
                .errorCode(e.getErrorCode())
                .developerMessage(e.toString())
                .userMessage(e.getMessage())
                .build();
    }

}
