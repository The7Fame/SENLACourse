package eu.senla.naumovich.dao;

import eu.senla.naumovich.config.DaoConfig;
import eu.senla.naumovich.config.TestConfig;
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
@ContextConfiguration(classes = { TestConfig.class, DaoConfig.class })
public class RoleRepositoryTest {
    @Autowired
    RoleRepository repository;

    @Test
    public void createRecord() {
        Role role = Generator.createRole();
        repository.create(role);
        Assertions.assertTrue(repository.findById(role.getId()).isPresent());
        Assertions.assertEquals(role, repository.findById(role.getId()).get());
    }

    @Test
    public void updateRecord() {
        Role role = Generator.updateRole();
        repository.update(role);
        Assertions.assertTrue(repository.findById(role.getId()).isPresent());
        Assertions.assertEquals(role, repository.findById(role.getId()).get());
    }

    @Test
    public void deleteTest() {
        Role role = Generator.createRole();
        repository.create(role);
        repository.deleteById(role.getId());
        Assertions.assertEquals(repository.getAll().size(), 2);
    }
}
