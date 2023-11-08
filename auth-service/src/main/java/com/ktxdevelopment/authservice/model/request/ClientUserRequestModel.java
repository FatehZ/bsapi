package com.ktxdevelopment.authservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientUserRequestModel {
    String id;
    String name;
    Integer age;
}