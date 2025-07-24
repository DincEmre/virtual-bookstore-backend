package com.dincdev.virtualbookstore.elasticsearch.controller;

import com.dincdev.virtualbookstore.elasticsearch.document.BookDocument;
import com.dincdev.virtualbookstore.elasticsearch.repository.BookElasticsearchRepository;
import com.dincdev.virtualbookstore.elasticsearch.service.BookSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class BookSearchController {

    private final BookSearchService bookSearchService;

    @GetMapping
    public ResponseEntity<List<BookDocument>> searchBooks(@RequestParam String keyword) {
        List<BookDocument> results = bookSearchService.search(keyword);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/by-category")
    public ResponseEntity<List<BookDocument>> getByCategory(@RequestParam String category) {
        List<BookDocument> results = bookSearchService.getByCategory(category);
        return ResponseEntity.ok(results);
    }
}
