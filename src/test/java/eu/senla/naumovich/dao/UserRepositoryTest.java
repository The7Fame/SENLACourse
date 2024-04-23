package eu.senla.naumovich.dao;

import eu.senla.naumovich.TestConfig;
import eu.senla.naumovich.dao.impl.UserRepositoryImpl;
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
@ContextConfiguration(classes = { TestConfig.class, UserRepositoryImpl.class })
public class UserRepositoryTest {
    @Autowired
    UserRepository repository;

    @Test
    public void createRecord() {
        User user = Generator.createUser();
        repository.create(user);
        Assertions.assertEquals(user, repository.getById(user.getId()));
    }

    @Test
    public void updateRecord() {
        User user = Generator.updateUser();
        repository.update(user);
        Assertions.assertEquals(user, repository.getById(user.getId()));
    }

    @Test
    public void deleteTest() {
        User user = Generator.createUser();
        repository.delete(user);
        Assertions.assertNull(repository.getById(user.getId()));
    }
}
