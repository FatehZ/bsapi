package com.ktxdevelopment.notificationms.controller;

import com.ktxdevelopment.notificationms.model.BookCreatedMessage;
import com.ktxdevelopment.notificationms.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController("/api/v1/amqp")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/")
    public void sendMailToSubscribers(@RequestBody BookCreatedMessage message) {
        notificationService.sendEmailToSubscribers(message);
    }
}
