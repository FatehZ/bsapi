package com.ktxdevelopment.authorms.repo;


import com.ktxdevelopment.authorms.model.ClientAuthorRequestModel;
import com.ktxdevelopment.authorms.model.UserClientResponse;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AuthorRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserClientResponse saveAuthor(ClientAuthorRequestModel model) {
        int rowsAffected  = jdbcTemplate.update("INSERT INTO authors (id, name, age) VALUES (?, ?, ?)", model.getId(), model.getName(), model.getAge());
        if (rowsAffected > 0) return new UserClientResponse(model.getId());
        else return new UserClientResponse(null);   // If failed return null.
    }

    public void deleteBookOfAuthor(String bookId, String authorId) {
        if (!getBooksOfAuthor(authorId).contains(bookId)) throw new NotFoundException("Book not found");

        String sql = "DELETE FROM books WHERE id = ?";
        jdbcTemplate.update(sql, bookId);
    }

    List<String> getBooksOfAuthor(String authorId) {
        String sql = "SELECT id FROM books WHERE author_id = ?";
        return jdbcTemplate.queryForList(sql, String.class, authorId);
    }
}