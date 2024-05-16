package eu.senla.naumovich.service;

import eu.senla.naumovich.dao.repository.BookRepository;
import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.dto.book.BookDto;
import eu.senla.naumovich.entities.Book;
import eu.senla.naumovich.mapper.BookMapper;
import eu.senla.naumovich.services.impl.BookServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
        when(repository.getAll(1,2)).thenReturn(Collections.emptyList());
        List<BookDto> result = service.getAll(1,2);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void getByIdTest() {
        Book book = Generator.createBook();
        BookDto bookDto = Generator.createBookDto();
        when(repository.findById(1L)).thenReturn(Optional.ofNullable(book));
        when(mapper.toDto(book)).thenReturn(bookDto);
        BookDto result = service.getById(1L);
        Assertions.assertEquals(bookDto, result);
        verify(repository).findById(1L);
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
        when(repository.create(book)).thenReturn(book);
        when(mapper.toDto(book)).thenReturn(bookDto);
        BookDto result = service.create(bookDto);
        Assertions.assertEquals(bookDto, result);
        verify(repository).create(book);
        verify(mapper).toEntity(bookDto);
        verify(mapper).toDto(book);
    }

    @Test
    public void deleteTest() {
        Book book = Generator.createBook();
        doNothing().when(repository).deleteById(book.getId());
        service.delete(book.getId());
        verify(repository).deleteById(book.getId());
    }
}
