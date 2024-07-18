package eu.senla.naumovich.repositories.custom;

import eu.senla.naumovich.entities.Order;

import java.util.Optional;

public interface OrderDao {
    Optional<Order> getByUserAndOrderById(long userId,long orderId);
}
