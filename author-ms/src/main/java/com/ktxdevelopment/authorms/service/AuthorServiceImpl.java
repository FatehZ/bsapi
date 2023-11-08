package com.ktxdevelopment.authorms.service;

import com.ktxdevelopment.authorms.model.Author;
import com.ktxdevelopment.authorms.model.BookCreatedMessage;
import com.ktxdevelopment.authorms.model.ClientAuthorRequestModel;
import com.ktxdevelopment.authorms.model.GlobalClientResponse;
import com.ktxdevelopment.authorms.repo.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public GlobalClientResponse deleteBookOfAuthor(String bookId, String authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow();

        if (author.getBookList().contains(bookId)) {
            author.getBookList().remove(bookId);
            authorRepository.save(author);
            return new GlobalClientResponse(bookId);
        } else {
            return new GlobalClientResponse(null);
        }
    }

    @Override
    public GlobalClientResponse saveAuthor(ClientAuthorRequestModel request) {
        Author a = authorRepository.save(Author.builder()
                .age(request.getAge())
                .name(request.getName())
                .build());
        return new GlobalClientResponse(a.getId());
    }

    @Override
    public GlobalClientResponse saveBookToAuthor(String id, String authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow();
        author.getBookList().add(id);
        authorRepository.save(author);
        return new GlobalClientResponse(id);
    }
}
