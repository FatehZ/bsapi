package com.ktxdevelopment.userservice.exception.handler;

import com.ktxdevelopment.userservice.exception.ResourceNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.ktxdevelopment.userservice.model.constants.ExceptionConstants.UNEXPECTED_EXCEPTION;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Log4j2
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ExceptionResponse handle(Exception ex) {
        log.error("Exception: ", ex);
        return new ExceptionResponse(UNEXPECTED_EXCEPTION);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ExceptionResponse handle(ResourceNotFoundException ex) {
        log.error("ResourceNotFoundException: ", ex);
        return getExceptionResponse(ex.getCode(), ex.getMessage());
    }

    private ExceptionResponse getExceptionResponse(String code, String message) {
        return new ExceptionResponse(code, message);
    }
}
