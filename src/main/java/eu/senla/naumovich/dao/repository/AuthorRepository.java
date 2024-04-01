package eu.senla.naumovich.dao.repository;

import eu.senla.naumovich.entities.Author;

import java.util.List;

public interface AuthorRepository {
    List<Author> getAll();

    Author getById(Author author);

    Author update(Author author);

    Author create(Author author);

    void delete(Author author);
}
