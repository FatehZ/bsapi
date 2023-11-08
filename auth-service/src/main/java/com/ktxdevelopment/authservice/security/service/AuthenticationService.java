package com.ktxdevelopment.authservice.security.service;

import com.ktxdevelopment.authservice.client.AuthorFeignClient;
import com.ktxdevelopment.authservice.client.StudentFeignClient;
import com.ktxdevelopment.authservice.exceptions.AuthRequestIncorrectException;
import com.ktxdevelopment.authservice.exceptions.UserNotFoundException;
import com.ktxdevelopment.authservice.model.entity.Role;
import com.ktxdevelopment.authservice.model.entity.Token;
import com.ktxdevelopment.authservice.model.entity.UserEntity;
import com.ktxdevelopment.authservice.model.request.AuthenticationRequest;
import com.ktxdevelopment.authservice.model.request.ClientUserRequestModel;
import com.ktxdevelopment.authservice.model.request.RegisterRequest;
import com.ktxdevelopment.authservice.model.response.AuthenticationResponse;
import com.ktxdevelopment.authservice.model.response.TokenValidationResponse;
import com.ktxdevelopment.authservice.repo.TokenRepository;
import com.ktxdevelopment.authservice.repo.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.InternalServerErrorException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    UserRepository userRepository;
    StudentFeignClient studentClient;
    AuthorFeignClient authorClient;

    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @SneakyThrows
    public AuthenticationResponse register(RegisterRequest request, Role role) {

        var user = UserEntity.builder()
                .id(UUID.randomUUID().toString())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();

        UserEntity savedUser = userRepository.save(user);

        String id;

        if (role == Role.STUDENT) {
            id = studentClient.saveStudent(new ClientUserRequestModel(user.getId(), request.getName(), request.getAge())).getId();
        }else{
            id = authorClient.saveAuthor(new ClientUserRequestModel(user.getId(), request.getName(), request.getAge())).getId();
        }

        if (id == null) {
            userRepository.delete(savedUser);
            throw new InternalServerErrorException("API error");   // basic transaction. may create retry mechanism, or use saga pattern
        }

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        return new AuthenticationResponse(jwtToken, refreshToken);
    }




    @SneakyThrows
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        var user = userRepository.findByEmail(request.getEmail()).orElseThrow(UserNotFoundException::new);

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void saveUserToken(UserEntity userEntity, String jwtToken) {
        var token = Token.builder()
                .userEntity(userEntity)
                .token(jwtToken)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(UserEntity userEntity) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(userEntity.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }


    @SneakyThrows
    public AuthenticationResponse refreshToken(HttpServletRequest request, HttpServletResponse response) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userId;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            throw new AuthRequestIncorrectException();
        }

        refreshToken = authHeader.substring(7);
        userId = jwtService.extractUsername(refreshToken);
        if (userId != null) {
            var user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

            if (jwtService.isTokenValid(refreshToken, user)) {

                var accessToken = jwtService.generateToken(user);

                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);

                return AuthenticationResponse.builder().accessToken(accessToken).refreshToken(refreshToken).build();
            }
        }{ throw new AuthRequestIncorrectException(); }
    }

    public TokenValidationResponse validateAndExtractRole(String token) {
        Token jwt = tokenRepository.findByToken(token).orElseThrow(UserNotFoundException::new);
        if (jwt.isExpired() || jwt.isRevoked()) {
            throw new CredentialsExpiredException("Token expired");
        }
        return TokenValidationResponse.builder().uid(jwt.getUserEntity().getId()).role(jwt.getUserEntity().getRole().name()).build();
    }
}