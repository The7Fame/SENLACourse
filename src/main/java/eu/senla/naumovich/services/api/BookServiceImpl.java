package eu.senla.naumovich.services.api;

import eu.senla.naumovich.dao.repository.BookRepository;
import eu.senla.naumovich.dto.BookDto;
import eu.senla.naumovich.entities.Book;
import eu.senla.naumovich.services.service.BookService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<BookDto> getAll() {
        List<BookDto> booksDto = new ArrayList<>();
        List<Book> books = bookRepository.getAll();
        for(Book book : books){
            booksDto.add(modelMapper.map(book, BookDto.class));
        }
        return booksDto;
    }

    @Override
    public BookDto getById(BookDto book) {
        return modelMapper.map(bookRepository.getById(modelMapper.map(book, Book.class)), BookDto.class);
    }

    @Override
    public BookDto update(BookDto book) {
        return modelMapper.map(bookRepository.update(modelMapper.map(book, Book.class)), BookDto.class);
    }

    @Override
    public BookDto create(BookDto book) {
        return modelMapper.map(bookRepository.create(modelMapper.map(book, Book.class)), BookDto.class);
    }

    @Override
    public void delete(BookDto book) {
        bookRepository.delete(modelMapper.map(book, Book.class));
    }
}
