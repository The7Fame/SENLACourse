package eu.senla.naumovich.dao;

import eu.senla.naumovich.config.DaoConfig;
import eu.senla.naumovich.config.TestConfig;
import eu.senla.naumovich.dao.repository.PublisherRepository;
import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.entities.Publisher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { TestConfig.class, DaoConfig.class })
public class PublisherRepositoryTest {
    @Autowired
    PublisherRepository repository;

    @Test
    public void createRecord() {
        Publisher publisher = Generator.createPublisher();
        repository.create(publisher);
        Assertions.assertTrue(repository.findById(publisher.getId()).isPresent());
        Assertions.assertEquals(publisher, repository.findById(publisher.getId()).get());
    }

    @Test
    public void updateRecord() {
        Publisher publisher = Generator.updatePublisher();
        repository.update(publisher);
        Assertions.assertTrue(repository.findById(publisher.getId()).isPresent());
        Assertions.assertEquals(publisher, repository.findById(publisher.getId()).get());
    }

    @Test
    public void deleteTest() {
        Publisher publisher = Generator.createPublisher();
        repository.create(publisher);
        repository.deleteById(publisher.getId());
        Assertions.assertEquals(repository.getAll().size(), 2);
    }
}
