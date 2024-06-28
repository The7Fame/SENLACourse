package eu.senla.naumovich.dao;

import eu.senla.naumovich.dao.common.BaseRepositoryTest;
import eu.senla.naumovich.entities.Order;
import eu.senla.naumovich.repositories.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.Optional;


public class OrderRepositoryTest extends BaseRepositoryTest {
    @Autowired
    OrderRepository repository;

    @Test
    public void getOrdersByUserId(){
        Page<Order> orderPage = repository.getOrdersByUserId(1, applyPage());
        Assertions.assertEquals(orderPage.getTotalElements(), 1);
    };

    @Test
    public void getByUserAndOrderById(){
        Optional<Order> order = repository.getByUserAndOrderById(1, 1);
        Assertions.assertTrue(order.isPresent());
    };
}
