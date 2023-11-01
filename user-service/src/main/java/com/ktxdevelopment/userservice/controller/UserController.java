package com.ktxdevelopment.userservice.controller;

import com.ktxdevelopment.userservice.model.request.UserRequest;
import com.ktxdevelopment.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void saveUser(@RequestBody UserRequest userRequest) {
        userService.saveUser(userRequest);
    }

}
