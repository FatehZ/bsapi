package com.ktxdevelopment.gatewayservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class TokenValidationResponse {
    private String uid;
    private String role;
    private String error;
}

