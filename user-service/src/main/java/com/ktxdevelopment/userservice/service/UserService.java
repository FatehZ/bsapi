package com.ktxdevelopment.userservice.service;

import com.ktxdevelopment.userservice.model.entity.UserEntity;
import com.ktxdevelopment.userservice.exception.ResourceNotFoundException;
import com.ktxdevelopment.userservice.model.client.UserClientResponse;
import com.ktxdevelopment.userservice.model.request.UserRequest;
import com.ktxdevelopment.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.ktxdevelopment.userservice.mapper.UserMapper.mapEntityToEntity;
import static com.ktxdevelopment.userservice.model.constants.ExceptionConstants.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void saveUser(UserRequest userRequest) {
        UserEntity userEntity = mapEntityToEntity(userRequest);
        userRepository.save(userEntity);
    }

    public UserClientResponse getUserByUsername(String email) {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND, HttpStatus.NOT_FOUND));

        return new UserClientResponse(userEntity.getId());
    }

}
