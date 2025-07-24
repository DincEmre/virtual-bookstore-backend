package com.dincdev.virtualbookstore.service.external;

import com.dincdev.virtualbookstore.dto.external.OpenLibraryBookDTO;
import com.dincdev.virtualbookstore.dto.external.OpenLibrarySearchResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OpenLibraryService {

    private final RestTemplate restTemplate = new RestTemplate();

    public List<OpenLibraryBookDTO> getBooksFromOpenLibraryApi(String keyword) {
        String url = "https://openlibrary.org/search.json?q=" + keyword;
        OpenLibrarySearchResponseDTO response = restTemplate.getForObject(url, OpenLibrarySearchResponseDTO.class);
        return response != null ? response.getDocs() : List.of();
    }
}