package eu.senla.naumovich.dao;

import eu.senla.naumovich.TestConfig;
import eu.senla.naumovich.dao.impl.PublisherRepositoryImpl;
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
@ContextConfiguration(classes = { TestConfig.class, PublisherRepositoryImpl.class })
public class PublisherRepositoryTest {
    @Autowired
    PublisherRepository repository;

    @Test
    public void createRecord() {
        Publisher publisher = Generator.createPublisher();
        repository.create(publisher);
        Assertions.assertEquals(publisher, repository.getById(publisher.getId()));
    }

    @Test
    public void updateRecord() {
        Publisher publisher = Generator.updatePublisher();
        repository.update(publisher);
        Assertions.assertEquals(publisher, repository.getById(publisher.getId()));
    }

    @Test
    public void deleteTest() {
        Publisher publisher = Generator.createPublisher();
        repository.delete(publisher);
        Assertions.assertNull(repository.getById(publisher.getId()));
    }
}
