package com.ktxdevelopment.studentms.controller;

import com.ktxdevelopment.studentms.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/subscribe", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> subscribeToAuthor(@RequestParam("uid") String studentId, @RequestParam("aid") String authorId) {
        return ResponseEntity.ok(studentService.subscribeToAuthor(studentId, authorId));
    }
}
