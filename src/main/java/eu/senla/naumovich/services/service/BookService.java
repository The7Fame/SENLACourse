package eu.senla.naumovich.services.service;

import eu.senla.naumovich.dto.BookDto;

import java.util.List;

public interface BookService {
    List<BookDto> getAll();

    BookDto getById(BookDto book);

    BookDto update(BookDto book);

    BookDto create(BookDto book);

    void delete(BookDto book);
}
