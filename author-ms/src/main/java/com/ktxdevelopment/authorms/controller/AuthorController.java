package com.ktxdevelopment.authorms.controller;

import com.ktxdevelopment.authorms.model.ClientAuthorRequestModel;
import com.ktxdevelopment.authorms.model.GlobalClientResponse;
import com.ktxdevelopment.authorms.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/internal/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public GlobalClientResponse saveAuthor(@RequestBody ClientAuthorRequestModel request) {
        return authorService.saveAuthor(request);
    }

    @PostMapping(value = "/book", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public GlobalClientResponse deleteBook(@PathVariable String id, @RequestHeader String uid) {
        return authorService.deleteBookOfAuthor(id, uid);
    }

    @PostMapping(value = "/book", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public GlobalClientResponse saveBook(@RequestParam String id, @RequestParam String uid) {
        return authorService.saveBookToAuthor(id, uid);
    }
}