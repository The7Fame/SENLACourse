package eu.senla.naumovich.services.api;

import eu.senla.naumovich.dao.repository.OrderRepository;
import eu.senla.naumovich.dto.OrderDto;
import eu.senla.naumovich.entities.Order;
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
        List<Order> orders = orderRepository.getAll();
        List<OrderDto> ordersDto = orders.stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
        return ordersDto;
    }

    @Override
    public OrderDto getById(OrderDto order) {
        return orderMapper.toDto(orderRepository.getById(orderMapper.toEntity(order)));
    }

    @Override
    public OrderDto update(OrderDto order) {
        return orderMapper.toDto(orderRepository.update(orderMapper.toEntity(order)));
    }

    @Override
    public OrderDto create(OrderDto order) {
        return orderMapper.toDto(orderRepository.create(orderMapper.toEntity(order)));
    }

    @Override
    public void delete(OrderDto order) {
        orderRepository.delete(orderMapper.toEntity(order));
    }
}
