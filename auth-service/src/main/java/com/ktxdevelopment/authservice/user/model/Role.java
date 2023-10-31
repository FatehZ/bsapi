package com.ktxdevelopment.authservice.user.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public enum Role {

  GUEST(Collections.emptySet()),

  USER(Set.of("USER")),

  MANAGER(Set.of("MANAGER", USER.name())),

  ADMIN(Set.of("ADMIN", MANAGER.name(), USER.name()));


  @Getter
  private final Set<String> permissions;

  public List<SimpleGrantedAuthority> getAuthorities() {
    return getPermissions()
            .stream()
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
  }
}

