package com.dincdev.virtualbookstore.controller.external;

import com.dincdev.virtualbookstore.dto.external.OpenLibraryBookDTO;
import com.dincdev.virtualbookstore.service.external.OpenLibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/openlibrary")
@RequiredArgsConstructor
public class OpenLibraryController {

    private final OpenLibraryService openLibraryService;

    @GetMapping("/search")
    public ResponseEntity<List<OpenLibraryBookDTO>> getBooksFromOpenLibraryApi(@RequestParam String keyword) {
        List<OpenLibraryBookDTO> results = openLibraryService.getBooksFromOpenLibraryApi(keyword);
        return ResponseEntity.ok(results);
    }
}