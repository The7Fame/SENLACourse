package eu.senla.naumovich.services.impl;

import eu.senla.naumovich.dao.repository.BookRepository;
import eu.senla.naumovich.dto.BookDto;
import eu.senla.naumovich.entities.Book;
import eu.senla.naumovich.exceptions.NoRecords;
import eu.senla.naumovich.mapper.BookMapper;
import eu.senla.naumovich.services.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public List<BookDto> getAll() {
        try {
            List<Book> books = bookRepository.getAll();
            List<BookDto> booksDto = books.stream()
                    .map(bookMapper::toDto)
                    .collect(Collectors.toList());
            return booksDto;
        } catch (Exception e) {
            throw new NoRecords("No records");
        }
    }

    @Override
    public BookDto getById(Long id) {
        try {
            return bookMapper.toDto(bookRepository.getById(id));
        } catch (Exception e) {
            throw new NoRecords("No record with such ID " + id);
        }
    }

    @Override
    public BookDto update(BookDto book) {
        try {
            return bookMapper.toDto(bookRepository.update(bookMapper.toEntity(book)));
        } catch (Exception e) {
            throw new NoRecords("No record with such ID " + book.getId());
        }

    }

    @Override
    public void create(BookDto book) {
        try {
            bookRepository.create(bookMapper.toEntity(book));
        } catch (Exception e) {
            throw new NoRecords("Cannot create record");
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Book book = bookRepository.getById(id);
            bookRepository.delete(book);
        } catch (Exception e) {
            throw new NoRecords("No record with such ID " + id);
        }
    }
}
