package com.ktxdevelopment.notificationms.service;

import com.ktxdevelopment.notificationms.model.BookCreatedMessage;

public interface NotificationService {

    void sendEmailToSubscribers(BookCreatedMessage authorId);
}
