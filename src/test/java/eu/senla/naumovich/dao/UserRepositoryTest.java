package eu.senla.naumovich.dao;

import eu.senla.naumovich.config.DaoConfig;
import eu.senla.naumovich.config.TestConfig;
import eu.senla.naumovich.dao.repository.UserRepository;
import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { TestConfig.class, DaoConfig.class })
public class UserRepositoryTest {
    @Autowired
    UserRepository repository;

    @Test
    public void createRecord() {
        User user = Generator.createUser();
        repository.create(user);
        Assertions.assertTrue(repository.findById(user.getId()).isPresent());
        Assertions.assertEquals(user, repository.findById(user.getId()).get());
    }

    @Test
    public void updateRecord() {
        User user = Generator.updateUser();
        repository.update(user);
        Assertions.assertTrue(repository.findById(user.getId()).isPresent());
        Assertions.assertEquals(user, repository.findById(user.getId()).get());
    }

    @Test
    public void deleteTest() {
        User user = Generator.createUser();
        repository.create(user);
        repository.deleteById(user.getId());
        Assertions.assertEquals(repository.getAll().size(), 2);
    }
}
