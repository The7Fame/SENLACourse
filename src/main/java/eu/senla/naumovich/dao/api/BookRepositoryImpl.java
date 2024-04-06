package eu.senla.naumovich.dao.api;

import eu.senla.naumovich.dao.repository.BookRepository;
import eu.senla.naumovich.entities.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepositoryImpl implements BookRepository {
    final List<Book> books = new ArrayList<>();
    @Override
    public List<Book> getAll() {
        return books;
    }

    @Override
    public Book getById(Book book) {
        return books.stream()
                .filter(b -> b.getId() == book.getId())
                .findFirst()
                .orElse(null);
    }

    @Override
    public Book update(Book book) {
        for(Book b : books){
            if(book.getId() == b.getId()){
                b.setPrice(book.getPrice());
                return b;
            }
        }
        return null;
    }

    @Override
    public Book create(Book book) {
        books.add(book);
        return book;
    }

    @Override
    public void delete(Book book) {
        books.removeIf(b -> b.getId() == book.getId());
    }
}
