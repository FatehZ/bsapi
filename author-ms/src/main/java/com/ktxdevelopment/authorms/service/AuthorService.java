package com.ktxdevelopment.authorms.service;

import com.ktxdevelopment.authorms.model.Author;
import com.ktxdevelopment.authorms.model.ClientAuthorRequestModel;
import com.ktxdevelopment.authorms.model.UserClientResponse;

public interface AuthorService {

    void deleteBookOfAuthor(String bookId, String authorId);

    UserClientResponse saveAuthor(ClientAuthorRequestModel request);
}
