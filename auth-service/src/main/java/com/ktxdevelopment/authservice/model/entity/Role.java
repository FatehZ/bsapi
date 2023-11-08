package com.ktxdevelopment.authservice.model.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum Role {

  STUDENT(Set.of("STUDENT")),

  AUTHOR(Set.of("AUTHOR"));


  private final Set<String> permissions;

  public List<SimpleGrantedAuthority> getAuthorities() {
    return getPermissions()
            .stream()
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
  }
}

