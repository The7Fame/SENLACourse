package eu.senla.naumovich.services;

import eu.senla.naumovich.dto.OrderDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> getAll();

    OrderDto getById(OrderDto order);

    OrderDto update(OrderDto order);

    OrderDto create(OrderDto order);

    void delete(OrderDto order);
}
