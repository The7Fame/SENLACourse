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

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
        when(repository.getAll(1,2)).thenReturn(Collections.emptyList());
        List<OrderDto> result = service.getAll(1,2);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void getByIdTest() {
        Order order = Generator.createOrder();
        OrderDto orderDto = Generator.createOrderDto();
        when(repository.findById(1L)).thenReturn(Optional.ofNullable(order));
        when(mapper.toDto(order)).thenReturn(orderDto);
        OrderDto result = service.getById(1L);
        Assertions.assertEquals(orderDto, result);
        verify(repository).findById(1L);
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
        when(repository.create(order)).thenReturn(order);
        when(mapper.toDto(order)).thenReturn(orderDto);
        OrderDto result = service.create(orderDto);
        Assertions.assertEquals(orderDto, result);
        verify(repository).create(order);
        verify(mapper).toEntity(orderDto);
        verify(mapper).toDto(order);
    }

    @Test
    public void deleteTest() {
        Order order = Generator.createOrder();
        doNothing().when(repository).deleteById(order.getId());
        service.delete(order.getId());
        verify(repository).deleteById(order.getId());
    }
}
