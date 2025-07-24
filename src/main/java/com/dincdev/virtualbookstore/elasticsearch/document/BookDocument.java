package com.dincdev.virtualbookstore.elasticsearch.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDocument {
    @Id
    private String id;
    private String title;
    private String author;
    private String category;
    private String description;
}