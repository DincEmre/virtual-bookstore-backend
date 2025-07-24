package com.dincdev.virtualbookstore.elasticsearch.service;

import com.dincdev.virtualbookstore.elasticsearch.document.BookDocument;
import com.dincdev.virtualbookstore.elasticsearch.repository.BookElasticsearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookSearchService {

    private final BookElasticsearchRepository bookElasticsearchRepository;

    public List<BookDocument> search(String keyword) {
        return bookElasticsearchRepository
                .findByTitleContainingIgnoreCaseOrCategoryContainingIgnoreCase(keyword, keyword);
    }

    public void index(BookDocument doc) {
        bookElasticsearchRepository.save(doc);
    }

    public List<BookDocument> getByCategory(String category) {
        return bookElasticsearchRepository.findByTitleContainingIgnoreCaseOrCategoryContainingIgnoreCase("", category);
    }

}

