package com.ktxdevelopment.userservice.controller.internal;

import com.ktxdevelopment.userservice.model.client.UserClientResponse;
import com.ktxdevelopment.userservice.model.request.UserRequest;
import com.ktxdevelopment.userservice.model.response.UserResponse;
import com.ktxdevelopment.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/internal/v1/users")
public class UserControllerInternal {

    private final UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    UserClientResponse getUserByEmail(@RequestParam String email) {
        return userService.getUserByUsername(email);
    }

    @PostMapping("/register")
    public UserClientResponse registerUser(@RequestBody UserRequest user) {
        return userService.register(user);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserResponse> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
        return ResponseEntity.ok(userService.updateUser(userId, updatedUser));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
