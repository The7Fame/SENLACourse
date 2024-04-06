package eu.senla.naumovich.services.api;

import eu.senla.naumovich.dao.repository.AuthorRepository;
import eu.senla.naumovich.dto.AuthorDto;
import eu.senla.naumovich.entities.Author;
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
    private final ModelMapper modelMapper;

    @Override
    public List<AuthorDto> getAll() {
        List<Author> authors = authorRepository.getAll();
        List<AuthorDto> authorsDto = authors.stream()
                .map(author -> modelMapper.map(author, AuthorDto.class))
                .collect(Collectors.toList());
        return authorsDto;
    }

    @Override
    public AuthorDto getById(AuthorDto author) {
        return modelMapper.map(authorRepository.getById(modelMapper.map(author, Author.class)), AuthorDto.class);
    }

    @Override
    public AuthorDto update(AuthorDto author) {
        return modelMapper.map(authorRepository.update(modelMapper.map(author, Author.class)), AuthorDto.class);
    }

    @Override
    public AuthorDto create(AuthorDto author) {
        return modelMapper.map(authorRepository.create(modelMapper.map(author, Author.class)), AuthorDto.class);
    }

    @Override
    public void delete(AuthorDto author) {
        authorRepository.delete(modelMapper.map(author, Author.class));
    }
}
