package eu.senla.naumovich.services;

import eu.senla.naumovich.dao.repository.AuthorRepository;
import eu.senla.naumovich.dto.AuthorDto;
import eu.senla.naumovich.entities.Author;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<AuthorDto> getAll() {
        List<AuthorDto> authorsDto = new ArrayList<>();
        List<Author> authors = authorRepository.getAll();
        for(Author author : authors){
            authorsDto.add(modelMapper.map(author, AuthorDto.class));
        }
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
