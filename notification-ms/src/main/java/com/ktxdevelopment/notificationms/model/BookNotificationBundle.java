package com.ktxdevelopment.notificationms.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BookNotificationBundle {
    private BookCreatedMessage message;
    private List<String> emails;
}
