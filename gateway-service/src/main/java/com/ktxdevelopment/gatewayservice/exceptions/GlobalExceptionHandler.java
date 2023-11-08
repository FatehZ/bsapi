package com.ktxdevelopment.gatewayservice.exceptions;

import com.ktxdevelopment.gatewayservice.model.AuthResponseCode;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ResponseEntity<Object> handle(ResourceNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }



    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public TokenValidationResponse handleUserNotFound(UserNotFoundException ex) {
        return TokenValidationResponse.builder().error(AuthResponseCode.NOT_FOUND.code).build();
    }


    @ExceptionHandler(CredentialsExpiredException.class)
    @ResponseStatus(UNAUTHORIZED)
    public TokenValidationResponse handleTokenExpired(CredentialsExpiredException ex) {
        return TokenValidationResponse.builder().error(AuthResponseCode.TOKEN_EXPIRED.code).build();
    }

}
