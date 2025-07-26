package com.dincdev.virtualbookstore.controller.external;

import com.dincdev.virtualbookstore.service.external.OpenLibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/openlibrary")
@RequiredArgsConstructor
public class OpenLibraryController {

    private final OpenLibraryService openLibraryService;

    @GetMapping("/search")
    public ResponseEntity<String> fetchAndSave(@RequestParam String keyword) {
        openLibraryService.fetchAndStoreBooks(keyword);
        return ResponseEntity.ok("Get books from OpenLibrary API and saved successfully! ");
    }
}