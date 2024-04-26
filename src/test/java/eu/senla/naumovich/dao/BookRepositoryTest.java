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
        Assertions.assertTrue(repository.findById(book.getId()).isPresent());
        Assertions.assertEquals(book, repository.findById(book.getId()).get());
    }

    @Test
    public void updateRecord() {
        Book book = Generator.updateBook();
        repository.update(book);
        Assertions.assertTrue(repository.findById(book.getId()).isPresent());
        Assertions.assertEquals(book, repository.findById(book.getId()).get());
    }

    @Test
    public void deleteTest() {
        Book book = Generator.createBook();
        repository.create(book);
        repository.deleteById(book.getId());
        Assertions.assertEquals(repository.getAll().size(), 2);
    }
}
