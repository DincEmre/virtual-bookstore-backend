package com.dincdev.virtualbookstore.service;

import com.dincdev.virtualbookstore.dto.BookRequestDTO;
import com.dincdev.virtualbookstore.dto.BookResponseDTO;
import com.dincdev.virtualbookstore.entity.Book;
import com.dincdev.virtualbookstore.mapper.BookMapper;
import com.dincdev.virtualbookstore.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookResponseDTO saveBook(BookRequestDTO dto) {
        Book book = bookMapper.toEntity(dto);
        Book saved = bookRepository.save(book);
        return bookMapper.toDto(saved);
    }

    public List<BookResponseDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return bookMapper.toDto(books);
    }

    public BookResponseDTO getBookById(@PathVariable Long id) {
        Book book = bookRepository.findById(id).orElseThrow();
        return bookMapper.toDto(book);
    }
}
