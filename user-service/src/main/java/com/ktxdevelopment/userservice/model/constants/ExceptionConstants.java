package com.ktxdevelopment.userservice.model.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionConstants {

    UNEXPECTED_EXCEPTION("UNEXPECTED_EXCEPTION", "Unexpected exception occurred"),

    USER_NOT_FOUND("USER_NOT_FOUND", "User not found");

    private final String code;
    private final String message;
}