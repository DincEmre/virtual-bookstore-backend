package com.dincdev.virtualbookstore.dto.external;

import lombok.Data;

import java.util.List;

@Data
public class OpenLibrarySearchResponseDTO {
    private List<OpenLibraryBookDTO> docs;
}

