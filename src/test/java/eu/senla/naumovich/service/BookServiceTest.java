package eu.senla.naumovich.service;

import eu.senla.naumovich.dao.repository.BookRepository;
import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.dto.BookDto;
import eu.senla.naumovich.entities.Book;
import eu.senla.naumovich.mapper.BookMapper;
import eu.senla.naumovich.services.impl.BookServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    private BookMapper mapper;
    @Mock
    BookRepository repository;
    @InjectMocks
    private BookServiceImpl service;

    @Test
    public void getAllTest() {
        Book book = Generator.createBook();
        BookDto bookDto = Generator.createBookDto();
        List<BookDto> bookDtos = new ArrayList<>();
        List<Book> books = new ArrayList<>();
        bookDtos.add(bookDto);
        books.add(book);
        when(repository.getAll()).thenReturn(books);
        when(mapper.toDto(book)).thenReturn(bookDto);
        List<BookDto> result = service.getAll();
        Assertions.assertEquals(bookDtos, result);
        verify(repository).getAll();
    }

    @Test
    public void getByIdTest() {
        Book book = Generator.createBook();
        BookDto bookDto = Generator.createBookDto();
        when(repository.getById(1L)).thenReturn(book);
        when(mapper.toDto(book)).thenReturn(bookDto);
        BookDto result = service.getById(1L);
        Assertions.assertEquals(bookDto, result);
        verify(repository).getById(1L);
        verify(mapper).toDto(book);
    }

    @Test
    public void updateTest() {
        Book book = Generator.createBook();
        BookDto bookDto = Generator.createBookDto();
        when(mapper.toEntity(bookDto)).thenReturn(book);
        when(repository.update(book)).thenReturn(book);
        when(mapper.toDto(book)).thenReturn(bookDto);
        BookDto result = service.update(bookDto);
        Assertions.assertEquals(bookDto, result);
        verify(repository).update(book);
        verify(mapper).toEntity(bookDto);
        verify(mapper).toDto(book);
    }

    @Test
    public void createTest() {
        Book book = Generator.createBook();
        BookDto bookDto = Generator.createBookDto();
        when(mapper.toEntity(bookDto)).thenReturn(book);
        doNothing().when(repository).create(book);
        service.create(bookDto);
        verify(repository).create(book);
        verify(mapper).toEntity(bookDto);
    }

    @Test
    public void deleteTest() {
        Book book = Generator.createBook();
        when(repository.getById(book.getId())).thenReturn(book);
        doNothing().when(repository).delete(book);
        service.delete(book.getId());
        verify(repository).getById(book.getId());
        verify(repository).delete(book);
    }
}
