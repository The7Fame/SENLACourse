package eu.senla.naumovich.dao;

import eu.senla.naumovich.dao.common.BaseRepositoryTest;
import eu.senla.naumovich.entities.User;
import eu.senla.naumovich.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class UserRepositoryTest extends BaseRepositoryTest {
    @Autowired
    private UserRepository repository;

    @Test
    void findByEmail(){
        User user = repository.findByEmail("unknown@email.com");
        Assertions.assertNull(user);
    };
}
