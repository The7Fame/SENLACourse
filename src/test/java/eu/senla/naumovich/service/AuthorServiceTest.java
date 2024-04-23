package eu.senla.naumovich.service;

import eu.senla.naumovich.dao.repository.AuthorRepository;
import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.dto.AuthorDto;
import eu.senla.naumovich.entities.Author;
import eu.senla.naumovich.mapper.AuthorMapper;
import eu.senla.naumovich.services.impl.AuthorServiceImpl;
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
public class AuthorServiceTest {
    @Mock
    private AuthorMapper mapper;
    @Mock
    AuthorRepository repository;
    @InjectMocks
    private AuthorServiceImpl service;

    @Test
    public void getAllTest() {
        Author author = Generator.createAuthor();
        AuthorDto authorDto = Generator.createAuthorDto();
        List<AuthorDto> authorDtos = new ArrayList<>();
        List<Author> authors = new ArrayList<>();
        authorDtos.add(authorDto);
        authors.add(author);
        when(repository.getAll()).thenReturn(authors);
        when(mapper.toDto(author)).thenReturn(authorDto);
        List<AuthorDto> result = service.getAll();
        Assertions.assertEquals(authorDtos, result);
        verify(repository).getAll();
    }

    @Test
    public void getByIdTest() {
        Author author = Generator.createAuthor();
        AuthorDto authorDto = Generator.createAuthorDto();
        when(repository.getById(1L)).thenReturn(author);
        when(mapper.toDto(author)).thenReturn(authorDto);
        AuthorDto result = service.getById(1L);
        Assertions.assertEquals(authorDto, result);
        verify(repository).getById(1L);
        verify(mapper).toDto(author);
    }

    @Test
    public void updateTest() {
        Author author = Generator.createAuthor();
        AuthorDto authorDto = Generator.createAuthorDto();
        when(mapper.toEntity(authorDto)).thenReturn(author);
        when(repository.update(author)).thenReturn(author);
        when(mapper.toDto(author)).thenReturn(authorDto);
        AuthorDto result = service.update(authorDto);
        Assertions.assertEquals(authorDto, result);
        verify(repository).update(author);
        verify(mapper).toEntity(authorDto);
        verify(mapper).toDto(author);
    }

    @Test
    public void createTest() {
        Author author = Generator.createAuthor();
        AuthorDto authorDto = Generator.createAuthorDto();
        when(mapper.toEntity(authorDto)).thenReturn(author);
        doNothing().when(repository).create(author);
        service.create(authorDto);
        verify(repository).create(author);
        verify(mapper).toEntity(authorDto);
    }

    @Test
    public void deleteTest() {
        Author author = Generator.createAuthor();

        when(repository.getById(author.getId())).thenReturn(author);
        doNothing().when(repository).delete(author);
        service.delete(author.getId());
        verify(repository).getById(author.getId());
        verify(repository).delete(author);
    }
}
