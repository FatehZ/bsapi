package com.ktxdevelopment.gatewayservice.client;


import com.ktxdevelopment.gatewayservice.model.TokenValidationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "auth-service",  url = "http://auth-service:8085")
public interface AuthFeignClient {

     @GetMapping("/api/v1/internal/auth/validate")
     TokenValidationResponse validate(@RequestParam String token);
}
