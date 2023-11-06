package com.ktxdevelopment.studentms.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user")
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Getter
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "userEntity")
    @ToString.Exclude
    private List<Token> tokens;
}