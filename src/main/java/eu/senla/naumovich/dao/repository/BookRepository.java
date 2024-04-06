package eu.senla.naumovich.dao.repository;

import eu.senla.naumovich.entities.Book;

import java.util.List;

public interface BookRepository {
    List<Book> getAll();

    Book getById(Book book);

    Book update(Book book);

    Book create(Book book);

    void delete(Book book);
}
