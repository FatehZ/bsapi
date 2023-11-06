package com.ktxdevelopment.authorms.controller;

import com.ktxdevelopment.authorms.model.ClientAuthorRequestModel;
import com.ktxdevelopment.authorms.model.UserClientResponse;
import com.ktxdevelopment.authorms.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/internal/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserClientResponse saveAuthor(@RequestBody ClientAuthorRequestModel request) {
        return authorService.saveAuthor(request);
    }
}