package com.ktxdevelopment.authservice.model.response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AuthResponseCode {
    NOT_FOUND("NOT_FOUND"),
    TOKEN_EXPIRED("TOKEN_EXPIRED");

    public final String code;
}
