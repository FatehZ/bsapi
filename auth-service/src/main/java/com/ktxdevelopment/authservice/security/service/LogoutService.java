package com.ktxdevelopment.authservice.security.service;

import com.ktxdevelopment.authservice.repo.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

    TokenRepository tokenRepo;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        // Sending jwt access token with Authorization header
        final String authHeader = request.getHeader("Authorization");
        final String jwt;

        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {return;}

        jwt = authHeader.substring(7);
        var storedToken = tokenRepo.findByToken(jwt).orElse(null);
        if (storedToken != null) {
            storedToken.setExpired(true);
            storedToken.setRevoked(true);
            tokenRepo.save(storedToken);
            SecurityContextHolder.clearContext();
        }
    }
}
