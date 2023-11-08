package com.ktxdevelopment.bookms.service;

import com.ktxdevelopment.bookms.client.AuthorFeignClient;
import com.ktxdevelopment.bookms.client.NotificationClient;
import com.ktxdevelopment.bookms.client.StudentFeignClient;
import com.ktxdevelopment.bookms.exception.InternalServerException;
import com.ktxdevelopment.bookms.exception.ResourceNotFoundException;
import com.ktxdevelopment.bookms.model.Book;
import com.ktxdevelopment.bookms.model.BookCreatedMessage;
import com.ktxdevelopment.bookms.model.GlobalClientResponse;
import com.ktxdevelopment.bookms.model.Student;
import com.ktxdevelopment.bookms.repo.BookRepository;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.InternalServerErrorException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepo;
    private final AuthorFeignClient authorFeignClient;
    private final StudentFeignClient studentFeignClient;
    private final NotificationClient notificationClient;

    @Override
    @Transactional
    public String deleteBookById(String id, String userId) {
        Book book = bookRepo.findById(id).orElseThrow(() -> ResourceNotFoundException.of("Book not found"));
        if (!book.getAuthorId().equals(userId)) throw ResourceNotFoundException.of("Book not found");

        bookRepo.delete(book);

        GlobalClientResponse res  = authorFeignClient.deleteBookFromAuthor(id, userId);  //todo
        if (res.getId() == null) {
            throw new InternalServerErrorException("Internal Server Error");  // retry or queue mechanism might be applied
        }
        return "Deleted Successfully";
    }

    @Override
    public List<Book> getBooksPaginated(Integer page) {
        Page<Book> books = bookRepo.findAll(PageRequest.of(page, 10));
        return books.stream().toList();
    }

    @Transactional
    @Override
    public GlobalClientResponse saveBook(String name, String authorId) {
        Book book = bookRepo.save(new Book(name, authorId));
        GlobalClientResponse res = authorFeignClient.saveBookToAuthor(book.getId(), authorId);
        if (res.getId() == null) throw new InternalServerErrorException("Internal Server Error");
        notificationClient.sendMail(new BookCreatedMessage(authorId, book.getId(), name, "Book " + book.getName() + " is introduced"));

        return res;
    }

    @Override
    public List<Student> getReadersOfBook(String bookId) {
        bookRepo.findById(bookId).orElseThrow(() -> ResourceNotFoundException.of("Book not found"));

        ResponseEntity<List<Student>> response = studentFeignClient.getAllReadersOfBook(bookId);
        if (response.getStatusCode() != HttpStatus.OK) throw new InternalServerException();
        return response.getBody();
    }
}