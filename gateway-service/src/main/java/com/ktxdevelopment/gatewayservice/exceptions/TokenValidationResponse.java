package com.ktxdevelopment.gatewayservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TokenValidationResponse {
    private String uid;
    private String role;
    private String error;
}