package eu.senla.naumovich.services.api;

import eu.senla.naumovich.dao.repository.BookRepository;
import eu.senla.naumovich.dto.BookDto;
import eu.senla.naumovich.entities.Book;
import eu.senla.naumovich.services.mapper.BookMapper;
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

    @Override
    public List<BookDto> getAll() {
        List<Book> books = bookRepository.getAll();
        List<BookDto> booksDto = books.stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
        return booksDto;
    }

    @Override
    public BookDto getById(BookDto book) {
        return bookMapper.toDto(bookRepository.getById(book.getId()));
    }

    @Override
    public BookDto update(BookDto book) {
        return bookMapper.toDto(bookRepository.update(bookMapper.toEntity(book)));
    }

    @Override
    public void create(BookDto book) {
        bookRepository.create(bookMapper.toEntity(book));
    }

    @Override
    public void delete(BookDto book) {
        bookRepository.delete(bookMapper.toEntity(book));
    }
}
