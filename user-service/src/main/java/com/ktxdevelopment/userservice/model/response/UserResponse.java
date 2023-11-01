package com.ktxdevelopment.userservice.model.response;

import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String password;
    private String email;
}
