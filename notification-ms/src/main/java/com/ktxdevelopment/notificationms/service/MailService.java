package com.ktxdevelopment.notificationms.service;

import com.ktxdevelopment.notificationms.config.RabbitMQConfig;
import com.ktxdevelopment.notificationms.model.BookNotificationBundle;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;

    @SneakyThrows
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        mailSender.send(message);
    }


    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void processSubscriberIds(BookNotificationBundle bundle) {
        for (String email : bundle.getEmails()) {
            String subject = "New Book";
            String text = "Book " + bundle.getMessage().getBookName() + " has been created by the author. Check it out!";
            sendEmail(email, subject, text);
        }
    }
}