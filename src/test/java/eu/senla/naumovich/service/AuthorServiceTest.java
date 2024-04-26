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

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
        when(repository.getAll()).thenReturn(Collections.emptyList());
        List<AuthorDto> result = service.getAll();
        Assertions.assertTrue(result.isEmpty());
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
        when(repository.create(author)).thenReturn(author);
        when(mapper.toDto(author)).thenReturn(authorDto);
        AuthorDto result = service.create(authorDto);
        Assertions.assertEquals(authorDto, result);
        verify(repository).create(author);
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
}
