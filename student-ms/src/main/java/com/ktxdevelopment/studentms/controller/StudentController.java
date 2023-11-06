package com.ktxdevelopment.studentms.controller;

import com.ktxdevelopment.studentms.model.ClientAuthorRequestModel;
import com.ktxdevelopment.studentms.model.UserClientResponse;
import com.ktxdevelopment.studentms.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/internal/author")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService authorService;

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserClientResponse saveAuthor(@RequestBody ClientAuthorRequestModel request) {
        return authorService.saveStudent(request);
    }
}