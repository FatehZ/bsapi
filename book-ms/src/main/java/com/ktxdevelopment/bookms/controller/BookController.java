package com.ktxdevelopment.bookms.controller;

import com.ktxdevelopment.bookms.model.Book;
import com.ktxdevelopment.bookms.model.GlobalClientResponse;
import com.ktxdevelopment.bookms.model.Student;
import com.ktxdevelopment.bookms.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") String id, @RequestHeader String uid) {
        return ResponseEntity.ok(bookService.deleteBookById(id, uid));
    }

    @GetMapping("/{id}/readers")
    public ResponseEntity<List<Student>> getReadersOfBook(@PathVariable String id) {
        return ResponseEntity.ok(bookService.getReadersOfBook(id));
    }


    @PostMapping("/")
    public ResponseEntity<GlobalClientResponse> createBook(
            @RequestParam("name") String name,
            @RequestHeader("uid") String authorId) {   // No need to create RequestBody, as there is only on param

        return ResponseEntity.ok(bookService.saveBook(name, authorId));
    }

    @GetMapping("/")
    public ResponseEntity<List<Book>> getAllBooks(@RequestParam("page") Integer page) {
        return ResponseEntity.ok(bookService.getBooksPaginated(page));
    }
}
