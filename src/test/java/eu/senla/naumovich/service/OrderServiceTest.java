package eu.senla.naumovich.service;

import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.dto.order.OrderDto;
import eu.senla.naumovich.dto.order.OrderShortDto;
import eu.senla.naumovich.entities.Order;
import eu.senla.naumovich.entities.User;
import eu.senla.naumovich.mapper.OrderMapper;
import eu.senla.naumovich.repositories.OrderRepository;
import eu.senla.naumovich.security.SecurityUser;
import eu.senla.naumovich.services.impl.OrderServiceImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock
    private OrderMapper mapper;
    @Mock
    private OrderRepository repository;
    @InjectMocks
    private OrderServiceImpl service;

    @Test
    void getAllTest() {
        String sort = "id";
        Pageable pageable = PageRequest.of(0, 5, Sort.by(sort));
        Order order = new Order();
        Page<Order> orderPage = new PageImpl<>(Collections.singletonList(order));
        when(repository.findAll(pageable)).thenReturn(orderPage);
        when(mapper.toDtoList(anyList())).thenReturn(Collections.singletonList(OrderShortDto.builder().build()));
        List<OrderShortDto> result = service.getAll(1, 5, sort);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(repository).findAll(pageable);
        verify(mapper).toDtoList(anyList());
    }

    @Test
    void getByIdTest() {
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
    void updateTest() {
        Order order = Generator.createOrder();
        OrderDto orderDto = Generator.createOrderDto();
        when(mapper.toEntity(orderDto)).thenReturn(order);
        when(repository.save(order)).thenReturn(order);
        when(mapper.toDto(order)).thenReturn(orderDto);
        OrderDto result = service.update(orderDto);
        Assertions.assertEquals(orderDto, result);
        verify(repository).save(order);
        verify(mapper).toEntity(orderDto);
        verify(mapper).toDto(order);
    }

    @Test
    void createTest() {
        Order order = Generator.createOrder();
        OrderDto orderDto = Generator.createOrderDto();
        when(mapper.toEntity(orderDto)).thenReturn(order);
        when(repository.save(order)).thenReturn(order);
        when(mapper.toDto(order)).thenReturn(orderDto);
        OrderDto result = service.create(orderDto);
        Assertions.assertEquals(orderDto, result);
        verify(repository).save(order);
        verify(mapper).toEntity(orderDto);
        verify(mapper).toDto(order);
    }

    @Test
    void deleteTest() {
        Order order = Generator.createOrder();
        doNothing().when(repository).deleteById(order.getId());
        service.delete(order.getId());
        verify(repository).deleteById(order.getId());
    }

    @Test
    void getUserOrdersTest(){
        Pageable pageable = PageRequest.of(0, 5);
        User user = Generator.createUser();
        SecurityUser securityUser = (SecurityUser) SecurityUser.fromUser(user);
        Page<Order> orderPage = new PageImpl<>(Collections.emptyList());
        when(repository.getOrdersByUserId(securityUser.getId(), PageRequest.of(0, 5))).thenReturn(orderPage);
        when(mapper.toDtoList(orderPage.getContent())).thenReturn(Collections.emptyList());
        List<OrderShortDto> result = service.getUserOrders(securityUser, 1, 5);

        assertNotNull(result);
        verify(repository).getOrdersByUserId(securityUser.getId(), pageable);
        verify(mapper).toDtoList(orderPage.getContent());
    }
}
