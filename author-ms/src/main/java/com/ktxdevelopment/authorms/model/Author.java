package com.ktxdevelopment.authorms.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class Author {

    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
