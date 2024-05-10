package eu.senla.naumovich.dao;

import eu.senla.naumovich.config.DaoConfig;
import eu.senla.naumovich.config.TestConfig;
import eu.senla.naumovich.dao.repository.AuthorRepository;
import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.entities.Author;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { TestConfig.class, DaoConfig.class })
public class AuthorRepositoryTest {
    @Autowired
    AuthorRepository repository;

    @Test
    public void createRecord() {
        Author author = Generator.createAuthor();
        repository.create(author);
        Assertions.assertTrue(repository.findById(author.getId()).isPresent());
        Assertions.assertEquals(author, repository.findById(author.getId()).get());
    }

    @Test
    public void updateRecord() {
        Author author = Generator.updateAuthor();
        repository.update(author);
        Assertions.assertTrue(repository.findById(author.getId()).isPresent());
        Assertions.assertEquals(author, repository.findById(author.getId()).get());
    }

    @Test
    public void deleteTest() {
        Author author = Generator.createAuthor();
        repository.create(author);
        repository.deleteById(author.getId());
        Assertions.assertEquals(repository.getAll(1,2).size(), 2);
    }
}
