package com.ktxdevelopment.authservice.user.service;

import com.ktxdevelopment.authservice.user.model.entity.User;
import com.ktxdevelopment.authservice.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    UserRepository userRepo;

    @Override
    public User findUserById(String userId) {
        return userRepo.findByUserId(userId).orElseThrow();
    }

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username).orElseThrow();
    }

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email).orElseThrow();
    }
}
