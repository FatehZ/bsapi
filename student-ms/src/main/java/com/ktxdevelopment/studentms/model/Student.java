package com.ktxdevelopment.studentms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@Entity
@Table(name = "students")
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    private String id;
    private String name;
    private Integer age;
    private List<String> subscriptions;

    @Column(name = "reading_books")
    private List<String> readingBooks;
}
