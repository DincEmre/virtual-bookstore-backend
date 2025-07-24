package com.dincdev.virtualbookstore.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class OpenLibraryBookDTO {
    private String title;

    @JsonProperty("author_name")
    private List<String> authorName;

    @JsonProperty("first_publish_year")
    private Integer firstPublishYear;

    private List<String> isbn;
}