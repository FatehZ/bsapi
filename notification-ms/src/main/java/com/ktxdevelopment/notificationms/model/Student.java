package com.ktxdevelopment.notificationms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private String id;
    private String name;
    private Integer age;
    private List<String> subscriptions;
    private List<String> readingBooks;
}
