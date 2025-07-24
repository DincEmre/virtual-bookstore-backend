package com.dincdev.virtualbookstore.elasticsearch.repository;

import com.dincdev.virtualbookstore.elasticsearch.document.BookDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import java.util.List;

public interface BookElasticsearchRepository extends ElasticsearchRepository<BookDocument, String> {
    List<BookDocument> findByTitleContainingIgnoreCaseOrCategoryContainingIgnoreCase(String title, String category);
}
