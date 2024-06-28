package eu.senla.naumovich.service;

import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.dto.author.AuthorDto;
import eu.senla.naumovich.dto.author.AuthorShortDto;
import eu.senla.naumovich.dto.book.BookShortDto;
import eu.senla.naumovich.entities.Author;
import eu.senla.naumovich.entities.Book;
import eu.senla.naumovich.mapper.AuthorMapper;
import eu.senla.naumovich.mapper.BookMapper;
import eu.senla.naumovich.repositories.AuthorRepository;
import eu.senla.naumovich.repositories.BookRepository;
import eu.senla.naumovich.services.impl.AuthorServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {
    @Mock
    private AuthorMapper mapper;
    @Mock
    private BookMapper bookMapper;
    @Mock
    AuthorRepository repository;
    @Mock
    BookRepository bookRepository;
    @InjectMocks
    private AuthorServiceImpl service;

    @Test
    public void getAllTest() {
        String sort = "id";
        Pageable pageable = PageRequest.of(0, 5, Sort.by(sort));
        Author author = new Author();
        Page<Author> addressPage = new PageImpl<>(Collections.singletonList(author));
        when(repository.findAll(pageable)).thenReturn(addressPage);
        when(mapper.toDtoList(anyList())).thenReturn(Collections.singletonList(AuthorShortDto.builder().build()));
        List<AuthorShortDto> result = service.getAll(1, 5, sort);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(repository).findAll(pageable);
        verify(mapper).toDtoList(anyList());
    }

    @Test
    public void getByIdTest() {
        Author author = Generator.createAuthor();
        AuthorDto authorDto = Generator.createAuthorDto();
        when(repository.findById(1L)).thenReturn(Optional.ofNullable(author));
        when(mapper.toDto(author)).thenReturn(authorDto);
        AuthorDto result = service.getById(1L);
        Assertions.assertEquals(authorDto, result);
        verify(repository).findById(1L);
        verify(mapper).toDto(author);
    }

    @Test
    public void updateTest() {
        Author author = Generator.createAuthor();
        AuthorDto authorDto = Generator.createAuthorDto();
        when(mapper.toEntity(authorDto)).thenReturn(author);
        when(repository.save(author)).thenReturn(author);
        when(mapper.toDto(author)).thenReturn(authorDto);
        AuthorDto result = service.update(authorDto);
        Assertions.assertEquals(authorDto, result);
        verify(repository).save(author);
        verify(mapper).toEntity(authorDto);
        verify(mapper).toDto(author);
    }

    @Test
    public void createTest() {
        Author author = Generator.createAuthor();
        AuthorDto authorDto = Generator.createAuthorDto();
        when(mapper.toEntity(authorDto)).thenReturn(author);
        when(repository.save(author)).thenReturn(author);
        when(mapper.toDto(author)).thenReturn(authorDto);
        AuthorDto result = service.create(authorDto);
        Assertions.assertEquals(authorDto, result);
        verify(repository).save(author);
        verify(mapper).toEntity(authorDto);
        verify(mapper).toDto(author);
    }

    @Test
    public void deleteTest() {
        Author author = Generator.createAuthor();
        doNothing().when(repository).deleteById(author.getId());
        service.delete(author.getId());
        verify(repository).deleteById(author.getId());
    }

    @Test
    public void getAuthorBooksTest() {
        String authorName = "SomeAuthor";
        Pageable pageable = PageRequest.of(0, 5);
        Book book = new Book();
        Page<Book> bookPage = new PageImpl<>(Collections.singletonList(book));
        when(bookRepository.getBooksByAuthorName(authorName, pageable)).thenReturn(bookPage);
        when(bookMapper.toDtoList(anyList())).thenReturn(Collections.singletonList(BookShortDto.builder().build()));
        List<BookShortDto> result = service.getAuthorBooks(1, 5, authorName);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(bookRepository).getBooksByAuthorName(authorName, pageable);
        verify(bookMapper).toDtoList(anyList());
    }
}
