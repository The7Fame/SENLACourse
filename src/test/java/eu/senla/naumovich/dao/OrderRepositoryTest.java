package eu.senla.naumovich.dao;

import eu.senla.naumovich.config.DaoConfig;
import eu.senla.naumovich.config.TestConfig;
import eu.senla.naumovich.dao.repository.OrderRepository;
import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.entities.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { TestConfig.class, DaoConfig.class })
public class OrderRepositoryTest {
    @Autowired
    OrderRepository repository;

    @Test
    public void createRecord() {
        Order order = Generator.createOrder();
        repository.create(order);
        Assertions.assertTrue(repository.findById(order.getId()).isPresent());
        Assertions.assertEquals(order, repository.findById(order.getId()).get());
    }

    @Test
    public void updateRecord() {
        Order order = Generator.updateOrder();
        repository.update(order);
        Assertions.assertTrue(repository.findById(order.getId()).isPresent());
        Assertions.assertEquals(order, repository.findById(order.getId()).get());
    }

    @Test
    public void deleteTest() {
        Order order = Generator.createOrder();
        repository.create(order);
        repository.deleteById(order.getId());
        Assertions.assertEquals(repository.getAll(1,2).size(), 2);
    }
}
