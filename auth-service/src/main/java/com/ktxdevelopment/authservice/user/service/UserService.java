package com.ktxdevelopment.authservice.user.service;

import com.ktxdevelopment.authservice.user.model.entity.User;

public interface UserService {

    User findUserById(String userId);

    User findByUsername(String username);

    User findByEmail(String email);
}
