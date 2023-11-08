package com.ktxdevelopment.bookms.client;

import com.ktxdevelopment.bookms.model.GlobalClientResponse;
import com.ktxdevelopment.bookms.model.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(name = "student-ms",  url = "http://student-ms:8083")
public interface StudentFeignClient {

    @GetMapping("/api/v1/internal/student/readers")
    ResponseEntity<List<Student>> getAllReadersOfBook(@RequestParam String id);
}