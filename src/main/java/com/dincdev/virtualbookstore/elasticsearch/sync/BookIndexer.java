package com.dincdev.virtualbookstore.elasticsearch.sync;

import com.dincdev.virtualbookstore.elasticsearch.document.BookDocument;
import com.dincdev.virtualbookstore.elasticsearch.repository.BookElasticsearchRepository;
import com.dincdev.virtualbookstore.entity.Book;
import com.dincdev.virtualbookstore.event.BookSyncEvent;
import com.dincdev.virtualbookstore.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookIndexer {

    private final BookRepository bookRepository;
    private final BookElasticsearchRepository elasticsearchRepository;
    private final ApplicationEventPublisher eventPublisher;

    @EventListener(ApplicationReadyEvent.class)
    @Retryable(
            value = {Exception.class}, // Özel bir exception sınıfı da yazabilirsin
            maxAttempts = 3,
            backoff = @Backoff(delay = 5000, multiplier = 2) // İlk denemeden sonra 5sn, sonra 10sn, sonra 20sn bekler
    )
    @Transactional
    public void indexBooksToElasticsearch() {
        log.info("Elasticsearch senkronizasyonu başlatıldı...");

        List<Book> booksFromDb = bookRepository.findAll();

        List<BookDocument> documents = booksFromDb.stream()
                .map(book -> BookDocument.builder()
                        .id(book.getId().toString())
                        .title(book.getTitle())
                        .author(book.getAuthor())
                        .category(book.getCategory().getName())  // Kategori varsa
                        .build())
                .collect(Collectors.toList());

        elasticsearchRepository.saveAll(documents);

        eventPublisher.publishEvent(new BookSyncEvent("Elasticsearch senkronizasyon işlemi.", documents.size()));
    }
}