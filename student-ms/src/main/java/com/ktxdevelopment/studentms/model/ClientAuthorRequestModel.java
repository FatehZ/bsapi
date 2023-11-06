package com.ktxdevelopment.studentms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class ClientAuthorRequestModel {
    String id;
    String name;
    Integer age;
}