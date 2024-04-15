package eu.senla.naumovich.dao;

import eu.senla.naumovich.TestConfig;
import eu.senla.naumovich.dao.repository.GenreRepository;
import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.entities.Genre;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { TestConfig.class })
public class GenreRepositoryTest {
    @Autowired
    GenreRepository repository;

    @Test
    public void createRecord() {
        Genre genre = Generator.createGenre();
        repository.create(genre);
        Assertions.assertEquals(genre, repository.getById(genre.getId()));
    }

    @Test
    public void updateRecord() {
        Genre genre = Generator.updateGenre();
        repository.update(genre);
        Assertions.assertEquals(genre, repository.getById(genre.getId()));
    }

    @Test
    public void deleteTest() {
        Genre genre = Generator.createGenre();
        repository.delete(genre);
        Assertions.assertNull(repository.getById(genre.getId()));
    }
}
