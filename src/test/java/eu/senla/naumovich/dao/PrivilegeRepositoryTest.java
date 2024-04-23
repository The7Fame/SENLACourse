package eu.senla.naumovich.dao;

import eu.senla.naumovich.TestConfig;
import eu.senla.naumovich.dao.impl.PrivilegeRepositoryImpl;
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
@ContextConfiguration(classes = { TestConfig.class, PrivilegeRepositoryImpl.class })
public class PrivilegeRepositoryTest {
    @Autowired
    PrivilegeRepository repository;

    @Test
    public void createRecord() {
        Privilege privilege = Generator.createPrivilege();
        repository.create(privilege);
        Assertions.assertEquals(privilege, repository.getById(privilege.getId()));
    }

    @Test
    public void updateRecord() {
        Privilege privilege = Generator.updatePrivilege();
        repository.update(privilege);
        Assertions.assertEquals(privilege, repository.getById(privilege.getId()));
    }

    @Test
    public void deleteTest() {
        Privilege privilege = Generator.createPrivilege();
        repository.delete(privilege);
        Assertions.assertNull(repository.getById(privilege.getId()));
    }
}
