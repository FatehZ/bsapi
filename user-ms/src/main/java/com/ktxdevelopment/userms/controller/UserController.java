package com.ktxdevelopment.userms.controller;

import com.ktxdevelopment.userms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController("/api/v1/internal/users")
public class UserController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/emails")
    List<String> getEmailsOfUsers(@RequestBody List<String> studentIds) {
        return userService.getEmailsOfUsers(studentIds);
    }
}
