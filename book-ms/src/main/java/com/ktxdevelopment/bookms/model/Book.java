package com.ktxdevelopment.bookms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Data
public class Book {
    public Book(String name, String authorId) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.authorId = authorId;
    }

    @Id
    String id;

    @Column(name = "name")
    String name;

    @Column(name = "author_id")
    String authorId;
}
