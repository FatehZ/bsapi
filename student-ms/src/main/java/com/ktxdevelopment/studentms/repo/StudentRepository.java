package com.ktxdevelopment.studentms.repo;

import com.ktxdevelopment.studentms.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    @Query("SELECT s FROM students s WHERE :id MEMBER OF s.reading_books")
    Optional<List<Student>> findUsersByReadingBook(@Param("id") String bookId);


    @Query("SELECT s.id FROM students s WHERE :id MEMBER OF s.subscriptions")
    Optional<List<String>> findUsersBySubscriberAuthor(@Param("id") String authorId);
}