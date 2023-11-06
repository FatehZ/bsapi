package com.ktxdevelopment.authorms.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum Role {

  STUDENT(Set.of("STUDENT")),

  AUTHOR(Set.of("AUTHOR"));


  private final Set<String> permissions;
}