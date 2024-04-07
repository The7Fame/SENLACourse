package eu.senla.naumovich.services.api;

import eu.senla.naumovich.dao.repository.AuthorRepository;
import eu.senla.naumovich.dto.AuthorDto;
import eu.senla.naumovich.entities.Author;
import eu.senla.naumovich.services.mapper.AuthorMapper;
import eu.senla.naumovich.services.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Override
    public List<AuthorDto> getAll() {
        List<Author> authors = authorRepository.getAll();
        List<AuthorDto> authorsDto = authors.stream()
                .map(authorMapper::toDto)
                .collect(Collectors.toList());
        return authorsDto;
    }

    @Override
    public AuthorDto getById(AuthorDto author) {
        return authorMapper.toDto(authorRepository.getById(authorMapper.toEntity(author)));
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
    public void delete(AuthorDto author) {
        authorRepository.delete(authorMapper.toEntity(author));
    }
}
