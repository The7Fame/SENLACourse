package eu.senla.naumovich.dao;

import eu.senla.naumovich.config.DaoConfig;
import eu.senla.naumovich.config.TestConfig;
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
@ContextConfiguration(classes = { TestConfig.class, DaoConfig.class })
public class AddressRepositoryTest {
    @Autowired
    AddressRepository repository;

    @Test
    public void createRecord() {
        Address address = Generator.createAddress();
        repository.create(address);
        Assertions.assertTrue(repository.findById(address.getId()).isPresent());
        Assertions.assertEquals(address, repository.findById(address.getId()).get());
    }

    @Test
    public void updateRecord() {
        Address address = Generator.updateAddress();
        repository.update(address);
        Assertions.assertTrue(repository.findById(address.getId()).isPresent());
        Assertions.assertEquals(address, repository.findById(address.getId()).get());
    }

    @Test
    public void deleteTest() {
        Address address = Generator.createAddress();
        repository.create(address);
        repository.deleteById(address.getId());
        Assertions.assertEquals(repository.getAll().size(), 2);
    }
}
