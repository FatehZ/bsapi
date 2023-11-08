package com.ktxdevelopment.authorms.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

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

    @ElementCollection
    @CollectionTable(name = "written_books", joinColumns = @JoinColumn(name = "author_id"))
    @Column(name = "book_id")
    private Set<String> bookList;


    public Set<String> getBookList() {
        if (bookList == null) bookList = new HashSet<>();
        return bookList;
    }
}
