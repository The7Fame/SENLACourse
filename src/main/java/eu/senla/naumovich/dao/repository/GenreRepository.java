package eu.senla.naumovich.dao.repository;

import eu.senla.naumovich.entities.Genre;

import java.util.List;

public interface GenreRepository {
    List<Genre> getAll();

    Genre getById(Genre genre);

    Genre update(Genre genre);

    Genre create(Genre genre);

    void delete(Genre genre);
}
