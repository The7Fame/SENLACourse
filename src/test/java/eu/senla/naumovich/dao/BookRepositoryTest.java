package eu.senla.naumovich.dao;

import eu.senla.naumovich.config.DaoConfig;
import eu.senla.naumovich.config.TestConfig;
import eu.senla.naumovich.dao.repository.BookRepository;
import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.entities.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { TestConfig.class, DaoConfig.class })
public class BookRepositoryTest {
    @Autowired
    BookRepository repository;

    @Test
    public void createRecord() {
        Book book = Generator.createBook();
        repository.create(book);
        Assertions.assertEquals(book, repository.getById(book.getId()));
    }

    @Test
    public void updateRecord() {
        Book book = Generator.updateBook();
        repository.update(book);
        Assertions.assertEquals(book, repository.getById(book.getId()));
    }

    @Test
    public void deleteTest() {
        Book book = Generator.createBook();
        repository.delete(book);
        Assertions.assertNull(repository.getById(book.getId()));
    }
}
