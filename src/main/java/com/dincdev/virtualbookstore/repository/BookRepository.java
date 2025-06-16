package com.dincdev.virtualbookstore.repository;

import com.dincdev.virtualbookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
