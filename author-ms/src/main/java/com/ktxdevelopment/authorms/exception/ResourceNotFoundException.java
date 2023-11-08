package com.ktxdevelopment.authorms.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class ResourceNotFoundException extends RuntimeException {

    private String message;

}