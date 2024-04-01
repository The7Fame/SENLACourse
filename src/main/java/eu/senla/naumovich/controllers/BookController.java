package eu.senla.naumovich.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.naumovich.dto.BookDto;
import eu.senla.naumovich.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final ObjectMapper objectMapper;

    public List<String> getAll() throws JsonProcessingException {
        List<BookDto> booksDto = bookService.getAll();
        List<String> booksJSON = new ArrayList<>();
        for(BookDto bookDto : booksDto){
            booksJSON.add(fromDtoToJSON(bookDto));
        }
        return booksJSON;
    }

    public String getById(String bookJSON) throws JsonProcessingException {
        return fromDtoToJSON(bookService.getById(fromJSONToDto(bookJSON)));
    }

    public String update(String bookJSON) throws JsonProcessingException {
        return fromDtoToJSON(bookService.update(fromJSONToDto(bookJSON)));
    }

    public String create(String bookJSON) throws JsonProcessingException {
        return fromDtoToJSON(bookService.create(fromJSONToDto(bookJSON)));
    }

    public void delete(String bookJSON) throws JsonProcessingException {
        bookService.delete(fromJSONToDto(bookJSON));
    }
    private BookDto fromJSONToDto(String bookJSON) throws JsonProcessingException {
        return objectMapper.readValue(bookJSON, BookDto.class);
    }

    private String fromDtoToJSON(BookDto bookDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(bookDto);
    }
}
