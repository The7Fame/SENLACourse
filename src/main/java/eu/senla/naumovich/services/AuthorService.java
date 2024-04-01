package eu.senla.naumovich.services;

import eu.senla.naumovich.dto.AuthorDto;

import java.util.List;

public interface AuthorService {
    List<AuthorDto> getAll();

    AuthorDto getById(AuthorDto author);

    AuthorDto update(AuthorDto author);

    AuthorDto create(AuthorDto author);

    void delete(AuthorDto author);
}
