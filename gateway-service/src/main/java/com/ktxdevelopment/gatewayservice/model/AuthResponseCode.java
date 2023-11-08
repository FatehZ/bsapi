package com.ktxdevelopment.gatewayservice.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AuthResponseCode {
    NOT_FOUND("NOT_FOUND"),
    TOKEN_EXPIRED("TOKEN_EXPIRED");

    public String code;
}
