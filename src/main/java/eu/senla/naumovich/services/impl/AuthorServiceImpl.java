package eu.senla.naumovich.services.impl;

import eu.senla.naumovich.dao.repository.AuthorRepository;
import eu.senla.naumovich.dto.author.AuthorDto;
import eu.senla.naumovich.entities.Author;
import eu.senla.naumovich.exceptions.NoRecords;
import eu.senla.naumovich.mapper.AuthorMapper;
import eu.senla.naumovich.services.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Override
    public List<AuthorDto> getAll(int size, int page) {
        List<Author> authors = authorRepository.getAll(size, page);
        return authorMapper.toDtoList(authors);
    }

    @Override
    public AuthorDto getById(Long id) {
        return authorMapper
                .toDto(authorRepository.findById(id).orElseThrow(() -> new NoRecords("No record with such ID " + id)));
    }

    @Override
    public AuthorDto update(AuthorDto author) {
        return authorMapper.toDto(authorRepository.update(authorMapper.toEntity(author)));
    }

    @Override
    public AuthorDto create(AuthorDto author) {
        return authorMapper.toDto(authorRepository.create(authorMapper.toEntity(author)));
    }

    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }
}
