package com.ktxdevelopment.authorms.service;

import com.ktxdevelopment.authorms.model.ClientAuthorRequestModel;
import com.ktxdevelopment.authorms.model.UserClientResponse;
import com.ktxdevelopment.authorms.repo.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    AuthorRepository authorRepository;

    @Override
    public void deleteBookOfAuthor(String bookId, String authorId) {
        authorRepository.deleteBookOfAuthor(bookId, authorId);
    }

    @Override
    public UserClientResponse saveAuthor(ClientAuthorRequestModel request) {
        return authorRepository.saveAuthor(request);
    }
}
