package com.ktxdevelopment.gatewayservice.filter;

import com.ktxdevelopment.gatewayservice.client.AuthFeignClient;
import com.ktxdevelopment.gatewayservice.model.AuthResponseCode;
import com.ktxdevelopment.gatewayservice.model.TokenValidationResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AuthenticationFilterGatewayFilterFactory extends AbstractGatewayFilterFactory<AuthenticationFilterGatewayFilterFactory.Config> {

    private final RouteValidator validator;
    private final AuthFeignClient authClient;

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("Missing authorization header");
                }

                String authHeader = Objects.requireNonNull(exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION)).get(0);

                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }

                TokenValidationResponse res = authClient.validate(authHeader);

                if (res.getUid() == null) {
                    if (Objects.equals(res.getError(), AuthResponseCode.TOKEN_EXPIRED.code)) {
                        throw new CredentialsExpiredException("Token expired");
                    } else {
                        throw new UsernameNotFoundException("User not found");
                    }
                }

                if (res.getRole().equals(config.getRequiredRole())) {
                    return chain.filter(exchange);
                } else {
                    exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                    return exchange.getResponse().setComplete();
                }
            }
            return chain.filter(exchange);
        });
    }

    @Data
    public static class Config {
        private String requiredRole;
    }
}

