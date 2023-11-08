package com.ktxdevelopment.authorms.service;

import com.ktxdevelopment.authorms.model.ClientAuthorRequestModel;
import com.ktxdevelopment.authorms.model.GlobalClientResponse;

public interface AuthorService {

    GlobalClientResponse deleteBookOfAuthor(String bookId, String authorId);

    GlobalClientResponse saveAuthor(ClientAuthorRequestModel request);

    GlobalClientResponse saveBookToAuthor(String id, String uid);
}
