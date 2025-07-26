package com.dincdev.virtualbookstore.service.external;

import com.dincdev.virtualbookstore.dto.external.OpenLibraryBookDTO;
import com.dincdev.virtualbookstore.dto.external.OpenLibrarySearchResponseDTO;
import com.dincdev.virtualbookstore.elasticsearch.document.BookDocument;
import com.dincdev.virtualbookstore.elasticsearch.repository.BookElasticsearchRepository;
import com.dincdev.virtualbookstore.entity.Book;
import com.dincdev.virtualbookstore.entity.Category;
import com.dincdev.virtualbookstore.repository.BookRepository;
import com.dincdev.virtualbookstore.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class OpenLibraryService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;
    private final BookElasticsearchRepository elasticRepository;

    public void fetchAndStoreBooks(String keyword) {
        String url = "https://openlibrary.org/search.json?q=" + keyword;
        OpenLibrarySearchResponseDTO response = restTemplate.getForObject(url, OpenLibrarySearchResponseDTO.class);

        if (response == null || response.getDocs() == null) return;

        for (OpenLibraryBookDTO dto : response.getDocs()) {
            String title = dto.getTitle();
            String author = (dto.getAuthorName() != null && !dto.getAuthorName().isEmpty())
                    ? dto.getAuthorName().get(0)
                    : "Unknown";

            String categoryName = (dto.getSubject() != null && !dto.getSubject().isEmpty())
                    ? dto.getSubject().get(0)
                    : "General";

            // Kategori kaydını kontrol et
            Category category = categoryRepository.findByNameIgnoreCase(categoryName)
                    .orElseGet(() -> categoryRepository.save(new Category(null, categoryName)));

            // Book Entity kaydet
            Book book = new Book(null, title, author, null, null, null, null, category);
            Book savedBook = bookRepository.save(book);

            // Elasticsearch'e kaydet
            BookDocument document = new BookDocument(
                    savedBook.getId().toString(),
                    title,
                    author,
                    categoryName,
                    null
            );
            elasticRepository.save(document);
        }
    }
}