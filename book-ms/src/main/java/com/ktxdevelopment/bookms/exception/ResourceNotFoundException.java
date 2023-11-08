package com.ktxdevelopment.bookms.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(staticName = "of")
@Getter
public class ResourceNotFoundException extends RuntimeException {

    private String message;

}