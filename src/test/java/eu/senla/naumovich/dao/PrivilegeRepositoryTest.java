package eu.senla.naumovich.dao;

import eu.senla.naumovich.config.DaoConfig;
import eu.senla.naumovich.config.TestConfig;
import eu.senla.naumovich.dao.repository.PrivilegeRepository;
import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.entities.Privilege;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { TestConfig.class, DaoConfig.class })
public class PrivilegeRepositoryTest {
    @Autowired
    PrivilegeRepository repository;

    @Test
    public void createRecord() {
        Privilege privilege = Generator.createPrivilege();
        repository.create(privilege);
        Assertions.assertTrue(repository.findById(privilege.getId()).isPresent());
        Assertions.assertEquals(privilege, repository.findById(privilege.getId()).get());
    }

    @Test
    public void updateRecord() {
        Privilege privilege = Generator.updatePrivilege();
        repository.update(privilege);
        Assertions.assertTrue(repository.findById(privilege.getId()).isPresent());
        Assertions.assertEquals(privilege, repository.findById(privilege.getId()).get());
    }

    @Test
    public void deleteTest() {
        Privilege privilege = Generator.createPrivilege();
        repository.create(privilege);
        repository.deleteById(privilege.getId());
        Assertions.assertEquals(repository.getAll().size(), 2);
    }
}
