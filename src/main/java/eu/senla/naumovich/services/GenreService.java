package eu.senla.naumovich.services;

import eu.senla.naumovich.dto.GenreDto;

import java.util.List;

public interface GenreService {
    List<GenreDto> getAll();

    GenreDto getById(GenreDto genre);

    GenreDto update(GenreDto genre);

    GenreDto create(GenreDto genre);

    void delete(GenreDto genre);
}
