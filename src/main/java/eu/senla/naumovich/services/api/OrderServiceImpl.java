package eu.senla.naumovich.services.api;

import eu.senla.naumovich.dao.repository.OrderRepository;
import eu.senla.naumovich.dto.OrderDto;
import eu.senla.naumovich.entities.Order;
import eu.senla.naumovich.services.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<OrderDto> getAll() {
        List<Order> orders = orderRepository.getAll();
        List<OrderDto> ordersDto = orders.stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .collect(Collectors.toList());
        return ordersDto;
    }

    @Override
    public OrderDto getById(OrderDto order) {
        return modelMapper.map(orderRepository.getById(modelMapper.map(order, Order.class)), OrderDto.class);
    }

    @Override
    public OrderDto update(OrderDto order) {
        return modelMapper.map(orderRepository.update(modelMapper.map(order, Order.class)), OrderDto.class);
    }

    @Override
    public OrderDto create(OrderDto order) {
        return modelMapper.map(orderRepository.create(modelMapper.map(order, Order.class)), OrderDto.class);
    }

    @Override
    public void delete(OrderDto order) {
        orderRepository.delete(modelMapper.map(order, Order.class));
    }
}
