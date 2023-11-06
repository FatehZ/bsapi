package com.ktxdevelopment.studentms.repo;


import com.ktxdevelopment.studentms.model.ClientAuthorRequestModel;
import com.ktxdevelopment.studentms.model.UserClientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StudentRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserClientResponse saveStudent(ClientAuthorRequestModel model) {
        int rowsAffected  = jdbcTemplate.update("INSERT INTO students (id, name, age) VALUES (?, ?, ?)", model.getId(), model.getName(), model.getAge());
        if (rowsAffected > 0) return new UserClientResponse(model.getId());
        else return new UserClientResponse(null);   // If failed return null.
    }
}