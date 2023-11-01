package com.ktxdevelopment.userservice.mapper;


import com.ktxdevelopment.userservice.model.entity.UserEntity;
import com.ktxdevelopment.userservice.model.request.UserRequest;
import com.ktxdevelopment.userservice.model.response.UserResponse;

public class UserMapper {
    public static UserResponse mapEntityToResponse(UserEntity userEntity) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(userEntity.getId());
        userResponse.setEmail(userEntity.getEmail());
        userResponse.setPassword(userEntity.getPassword());
        return userResponse;
    }

    public static UserEntity mapEntityToEntity(UserRequest userRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userRequest.getEmail());
        userEntity.setPassword(userEntity.getPassword());
        return userEntity;
    }
}
