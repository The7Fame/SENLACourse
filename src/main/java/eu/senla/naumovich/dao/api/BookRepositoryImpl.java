package eu.senla.naumovich.dao.api;

import eu.senla.naumovich.dao.repository.BookRepository;
import eu.senla.naumovich.dao.repository.common.AbstractDao;
import eu.senla.naumovich.entities.Book;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepositoryImpl extends AbstractDao<Long, Book> implements BookRepository {
    @Override
    protected Class<Book> getEntityClass() {
        return Book.class;
    }
}
