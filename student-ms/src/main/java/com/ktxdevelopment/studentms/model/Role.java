package com.ktxdevelopment.studentms.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Getter
@RequiredArgsConstructor
public enum Role {

  STUDENT(Set.of("STUDENT")),

  AUTHOR(Set.of("AUTHOR"));


  private final Set<String> permissions;
}