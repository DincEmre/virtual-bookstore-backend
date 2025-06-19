package com.dincdev.virtualbookstore.controller;

import com.dincdev.virtualbookstore.dto.BookRequestDTO;
import com.dincdev.virtualbookstore.dto.BookResponseDTO;
import com.dincdev.virtualbookstore.entity.Book;
import com.dincdev.virtualbookstore.repository.BookRepository;
import com.dincdev.virtualbookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<BookResponseDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public BookResponseDTO getBook(@PathVariable Long id) {
        return  bookService.getBookById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<BookResponseDTO> saveBook(@RequestBody BookRequestDTO requestDTO) {
        BookResponseDTO response = bookService.saveBook(requestDTO);
        return ResponseEntity.ok(response);
    }
}
