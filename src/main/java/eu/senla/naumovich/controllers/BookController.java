package eu.senla.naumovich.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.naumovich.dto.BookDto;
import eu.senla.naumovich.services.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final ObjectMapper objectMapper;

    public List<String> getAll() {
        List<BookDto> booksDto = bookService.getAll();
        List<String> booksJSON = booksDto.stream().map(this::fromDtoToJSON).collect(Collectors.toList());
        return booksJSON;
    }

    public String getById(String bookJSON) {
        return fromDtoToJSON(bookService.getById(fromJSONToDto(bookJSON)));
    }

    public String update(String bookJSON) {
        return fromDtoToJSON(bookService.update(fromJSONToDto(bookJSON)));
    }

    public void create(String bookJSON) {
        bookService.create(fromJSONToDto(bookJSON));
    }

    public void delete(String bookJSON) {
        bookService.delete(fromJSONToDto(bookJSON));
    }

    private BookDto fromJSONToDto(String bookJSON) {
        try {
            return objectMapper.readValue(bookJSON, BookDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String fromDtoToJSON(BookDto bookDto) {
        try {
            return objectMapper.writeValueAsString(bookDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
