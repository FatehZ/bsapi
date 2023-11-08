package com.ktxdevelopment.bookms.service;

import com.ktxdevelopment.bookms.model.Book;
import com.ktxdevelopment.bookms.model.GlobalClientResponse;
import com.ktxdevelopment.bookms.model.Student;

import java.util.List;

public interface BookService {
    
    String deleteBookById(String id, String userId);

    List<Book> getBooksPaginated(Integer page);

    GlobalClientResponse saveBook(String name, String authorId);

    List<Student> getReadersOfBook(String id);
}