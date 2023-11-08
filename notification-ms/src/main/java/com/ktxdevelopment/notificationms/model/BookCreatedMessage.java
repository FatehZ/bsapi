package com.ktxdevelopment.notificationms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookCreatedMessage implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String authorId;
    private String bookId;
    private String bookName;
    private String message;

}