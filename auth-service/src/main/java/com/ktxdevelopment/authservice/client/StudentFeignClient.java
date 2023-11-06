package com.ktxdevelopment.authservice.client;

import com.ktxdevelopment.authservice.model.ClientUserRequestModel;
import com.ktxdevelopment.authservice.model.UserClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "student-ms",  url = "http://student-ms:8083")
public interface StudentFeignClient {
    @PostMapping("/api/v1/student")
    UserClientResponse saveStudent(@RequestBody ClientUserRequestModel student);
}