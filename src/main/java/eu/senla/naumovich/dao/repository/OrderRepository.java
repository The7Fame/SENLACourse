package eu.senla.naumovich.dao.repository;

import eu.senla.naumovich.entities.Order;

import java.util.List;

public interface OrderRepository {
    List<Order> getAll();

    Order getById(Order order);

    Order update(Order order);

    Order create(Order order);

    void delete(Order order);
}
