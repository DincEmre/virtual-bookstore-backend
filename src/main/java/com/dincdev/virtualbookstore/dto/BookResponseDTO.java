package com.dincdev.virtualbookstore.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BookResponseDTO {
    private String title;
    private String description;
    private String isbn;
    private BigDecimal price;
    private String coverImageUrl;
}
