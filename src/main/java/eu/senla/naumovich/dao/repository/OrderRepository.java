package eu.senla.naumovich.dao.repository;

import eu.senla.naumovich.dao.repository.common.AbstractRepository;
import eu.senla.naumovich.entities.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends AbstractRepository<Order, Long> {
    List<Order> getOrdersByUserId(long userId, int page, int size);
    Optional<Order> getByUserAndOrderById(long userId, int orderId);
}
