package com.ktxdevelopment.bookms.repo;

import com.ktxdevelopment.bookms.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, String> {


}
