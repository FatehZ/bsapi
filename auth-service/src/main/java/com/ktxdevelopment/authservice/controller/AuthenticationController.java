package com.ktxdevelopment.authservice.controller;

import com.ktxdevelopment.authservice.model.AuthenticationRequest;
import com.ktxdevelopment.authservice.model.AuthenticationResponse;
import com.ktxdevelopment.authservice.model.RegisterRequest;
import com.ktxdevelopment.authservice.security.service.AuthenticationService;
import com.ktxdevelopment.authservice.model.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/")
@Slf4j
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping(value = "/register/student", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<AuthenticationResponse> registerStudent(@RequestBody RegisterRequest request) {
      return ResponseEntity.ok(service.register(request, Role.STUDENT));
  }

  @PostMapping(value = "/register/author", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<AuthenticationResponse> registerAuthor(@RequestBody RegisterRequest request) {
    return ResponseEntity.ok(service.register(request, Role.AUTHOR));
  }


  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
    return ResponseEntity.ok(service.authenticate(request));
  }

  @PostMapping("/refresh-token")
  public ResponseEntity<AuthenticationResponse> refreshToken(HttpServletRequest request, HttpServletResponse response) {
    return ResponseEntity.ok(service.refreshToken(request, response));
  }
}
