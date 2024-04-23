package eu.senla.naumovich.services.impl;

import eu.senla.naumovich.dao.repository.AuthorRepository;
import eu.senla.naumovich.dto.AuthorDto;
import eu.senla.naumovich.entities.Author;
import eu.senla.naumovich.exceptions.NoRecords;
import eu.senla.naumovich.mapper.AuthorMapper;
import eu.senla.naumovich.services.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Override
    public List<AuthorDto> getAll() {
        try {
            List<Author> authors = authorRepository.getAll();
            List<AuthorDto> authorsDto = authors.stream()
                    .map(authorMapper::toDto)
                    .collect(Collectors.toList());
            return authorsDto;
        } catch (Exception e) {
            throw new NoRecords("No records");
        }
    }

    @Override
    public AuthorDto getById(Long id) {
        try {
            return authorMapper.toDto(authorRepository.getById(id));
        } catch (Exception e) {
            throw new NoRecords("No record with such ID " + id);
        }
    }

    @Override
    public AuthorDto update(AuthorDto author) {
        try {
            return authorMapper.toDto(authorRepository.update(authorMapper.toEntity(author)));
        } catch (Exception e) {
            throw new NoRecords("No record with such ID " + author.getId());
        }
    }

    @Override
    public void create(AuthorDto author) {
        authorRepository.create(authorMapper.toEntity(author));
    }

    @Override
    public void delete(Long id) {
        try {
            Author author = authorRepository.getById(id);
            authorRepository.delete(author);
        } catch (Exception e) {
            throw new NoRecords("No record with such ID " + id);
        }

    }
}
