package eu.senla.naumovich.dao;

import eu.senla.naumovich.TestConfig;
import eu.senla.naumovich.dao.repository.RoleRepository;
import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.entities.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { TestConfig.class })
public class RoleRepositoryTest {
    @Autowired
    RoleRepository repository;

    @Test
    public void createRecord() {
        Role role = Generator.createRole();
        repository.create(role);
        Assertions.assertEquals(role, repository.getById(role.getId()));
    }

    @Test
    public void updateRecord() {
        Role role = Generator.updateRole();
        repository.update(role);
        Assertions.assertEquals(role, repository.getById(role.getId()));
    }

    @Test
    public void deleteTest() {
        Role role = Generator.createRole();
        repository.delete(role);
        Assertions.assertNull(repository.getById(role.getId()));
    }
}
