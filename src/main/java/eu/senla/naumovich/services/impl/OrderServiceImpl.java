package eu.senla.naumovich.services.impl;

import eu.senla.naumovich.dao.repository.OrderRepository;
import eu.senla.naumovich.dto.OrderDto;
import eu.senla.naumovich.entities.Order;
import eu.senla.naumovich.exceptions.NoRecords;
import eu.senla.naumovich.services.mapper.OrderMapper;
import eu.senla.naumovich.services.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public List<OrderDto> getAll() {
        try {
            List<Order> orders = orderRepository.getAll();
            List<OrderDto> ordersDto = orders.stream()
                    .map(orderMapper::toDto)
                    .collect(Collectors.toList());
            return ordersDto;
        } catch (Exception e) {
            throw new NoRecords("No records");
        }
    }

    @Override
    public OrderDto getById(Long id) {
        try {
            return orderMapper.toDto(orderRepository.getById(id));
        } catch (Exception e) {
            throw new NoRecords("No record with such ID " + id);
        }
    }

    @Override
    public OrderDto update(OrderDto order) {
        try {
            return orderMapper.toDto(orderRepository.update(orderMapper.toEntity(order)));
        } catch (Exception e) {
            throw new NoRecords("No record with such ID " + order.getId());
        }
    }

    @Override
    public void create(OrderDto order) {
        try {
            orderRepository.create(orderMapper.toEntity(order));
        } catch (Exception e) {
            throw new NoRecords("Cannot create record");
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Order order = orderRepository.getById(id);
            orderRepository.delete(order);
        } catch (Exception e) {
            throw new NoRecords("No record with such ID " + id);
        }
    }
}
