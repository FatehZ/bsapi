package com.ktxdevelopment.notificationms.service;


import com.ktxdevelopment.notificationms.client.StudentClient;
import com.ktxdevelopment.notificationms.client.UserClient;
import com.ktxdevelopment.notificationms.config.RabbitMQConfig;
import com.ktxdevelopment.notificationms.model.BookCreatedMessage;
import com.ktxdevelopment.notificationms.model.BookNotificationBundle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService{

    private final StudentClient studentClient;
    private final UserClient userClient;
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void sendEmailToSubscribers(BookCreatedMessage message) {
        List<String> list = studentClient.getAllSubscribers(message.getAuthorId());
        List<String> emails = userClient.getEmailsOfUsers(list);
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, new BookNotificationBundle(message, emails));
    }
}


