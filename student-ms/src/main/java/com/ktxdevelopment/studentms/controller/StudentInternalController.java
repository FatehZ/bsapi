package com.ktxdevelopment.studentms.controller;

import com.ktxdevelopment.studentms.model.ClientStudentRequestModel;
import com.ktxdevelopment.studentms.model.GlobalClientResponse;
import com.ktxdevelopment.studentms.model.Student;
import com.ktxdevelopment.studentms.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/internal/student")
@RequiredArgsConstructor
public class StudentInternalController {

    private final StudentService studentService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public GlobalClientResponse saveStudent(@RequestBody ClientStudentRequestModel request) {
        return studentService.saveStudent(request);
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/readers")
    public ResponseEntity<List<Student>> getAllReadersOfBook(@RequestParam("id") String bookId) {
        return ResponseEntity.ok(studentService.getAllReadersOfBook(bookId));
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/subscribers")
    public List<String> getAllSubscribersOfAuthor(@RequestParam("author_id") String authorId) {
        return studentService.getAllSubscribersOfAuthor(authorId);
    }
}