package com.dincdev.virtualbookstore.mapper;

import com.dincdev.virtualbookstore.dto.BookRequestDTO;
import com.dincdev.virtualbookstore.dto.BookResponseDTO;
import com.dincdev.virtualbookstore.entity.Book;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book toEntity(BookRequestDTO dto);
    BookResponseDTO toDto(Book entity);
    List<BookResponseDTO> toDto(List<Book> books);
}
