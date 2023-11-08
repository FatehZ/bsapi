package com.ktxdevelopment.bookms.client;

import com.ktxdevelopment.bookms.model.BookCreatedMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "notification-ms",  url = "http://student-ms:8090")
public interface NotificationClient {
    @PostMapping("/api/v1/amqp")
    void sendMail(@RequestBody BookCreatedMessage authorId);
}