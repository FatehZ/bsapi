package com.ktxdevelopment.notificationms.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "user-ms", url = "http://user-ms:8093")
public interface UserClient {

    @GetMapping("/api/v1/internal/users/emails")
    List<String> getEmailsOfUsers(@RequestBody List<String> studentIds);
}
