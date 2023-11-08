package com.ktxdevelopment.authorms.exception.handler;

import com.ktxdevelopment.authorms.exception.InternalServerException;
import com.ktxdevelopment.authorms.exception.ResourceNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Log4j2
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(InternalServerException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ExceptionResponse handle(InternalServerException ex) {
        return new ExceptionResponse("Unexpected Exception");
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ExceptionResponse handle(ResourceNotFoundException ex) {
        return new ExceptionResponse(ex.getMessage());
    }
}
