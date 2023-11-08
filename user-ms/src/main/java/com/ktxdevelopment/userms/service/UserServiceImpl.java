package com.ktxdevelopment.userms.service;

import com.ktxdevelopment.userms.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public List<String> getEmailsOfUsers(List<String> ids) {
        return userRepository.findEmailsByUserIds(ids);
    }
}
