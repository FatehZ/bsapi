package com.ktxdevelopment.authservice.controller;


import com.ktxdevelopment.authservice.model.response.TokenValidationResponse;
import com.ktxdevelopment.authservice.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/internal/auth")
@RequiredArgsConstructor
public class AuthInternalController {

    private final AuthenticationService authenticationService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/validate")
    public TokenValidationResponse validateToken(@RequestParam("token") String token) {
        return authenticationService.validateAndExtractRole(token);
    }
}
