package eu.senla.naumovich.dao;

import eu.senla.naumovich.TestConfig;
import eu.senla.naumovich.dao.repository.AddressRepository;
import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.entities.Address;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { TestConfig.class })
public class AddressRepositoryTest {
    @Autowired
    AddressRepository repository;

    @Test
    public void createRecord() {
        Address address = Generator.createAddress();
        repository.create(address);
        Assertions.assertEquals(address, repository.getById(address.getId()));
    }

    @Test
    public void updateRecord() {
        Address address = Generator.updateAddress();
        repository.update(address);
        Assertions.assertEquals(address, repository.getById(address.getId()));
    }

    @Test
    public void deleteTest() {
        Address address = Generator.createAddress();
        repository.delete(address);
        Assertions.assertNull(repository.getById(address.getId()));
    }
}
