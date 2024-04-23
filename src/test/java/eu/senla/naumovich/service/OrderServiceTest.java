package eu.senla.naumovich.service;

import eu.senla.naumovich.dao.repository.OrderRepository;
import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.dto.OrderDto;
import eu.senla.naumovich.entities.Order;
import eu.senla.naumovich.mapper.OrderMapper;
import eu.senla.naumovich.services.impl.OrderServiceImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @Mock
    private OrderMapper mapper;
    @Mock
    OrderRepository repository;
    @InjectMocks
    private OrderServiceImpl service;

    @Test
    public void getAllTest() {
        Order order = Generator.createOrder();
        OrderDto orderDto = Generator.createOrderDto();
        List<OrderDto> orderDtos = new ArrayList<>();
        List<Order> orders = new ArrayList<>();
        orderDtos.add(orderDto);
        orders.add(order);
        when(repository.getAll()).thenReturn(orders);
        when(mapper.toDto(order)).thenReturn(orderDto);
        List<OrderDto> result = service.getAll();
        Assertions.assertEquals(orderDtos, result);
        verify(repository).getAll();
    }

    @Test
    public void getByIdTest() {
        Order order = Generator.createOrder();
        OrderDto orderDto = Generator.createOrderDto();
        when(repository.getById(1L)).thenReturn(order);
        when(mapper.toDto(order)).thenReturn(orderDto);
        OrderDto result = service.getById(1L);
        Assertions.assertEquals(orderDto, result);
        verify(repository).getById(1L);
        verify(mapper).toDto(order);
    }

    @Test
    public void updateTest() {
        Order order = Generator.createOrder();
        OrderDto orderDto = Generator.createOrderDto();
        when(mapper.toEntity(orderDto)).thenReturn(order);
        when(repository.update(order)).thenReturn(order);
        when(mapper.toDto(order)).thenReturn(orderDto);
        OrderDto result = service.update(orderDto);
        Assertions.assertEquals(orderDto, result);
        verify(repository).update(order);
        verify(mapper).toEntity(orderDto);
        verify(mapper).toDto(order);
    }

    @Test
    public void createTest() {
        Order order = Generator.createOrder();
        OrderDto orderDto = Generator.createOrderDto();
        when(mapper.toEntity(orderDto)).thenReturn(order);
        doNothing().when(repository).create(order);
        service.create(orderDto);
        verify(repository).create(order);
        verify(mapper).toEntity(orderDto);
    }

    @Test
    public void deleteTest() {
        Order order = Generator.createOrder();
        when(repository.getById(order.getId())).thenReturn(order);
        doNothing().when(repository).delete(order);
        service.delete(order.getId());
        verify(repository).getById(order.getId());
        verify(repository).delete(order);
    }
}
