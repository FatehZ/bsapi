package com.ktxdevelopment.notificationms.client;

import com.ktxdevelopment.notificationms.model.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "student-ms", url = "http://student-ms:8083")
public interface StudentClient {

    @GetMapping("/api/v1/internal/students/subscribers")
    List<String> getAllSubscribers(@RequestParam("author_id") String authorId);
}


