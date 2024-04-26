package eu.senla.naumovich.services.impl;

import eu.senla.naumovich.dao.repository.OrderRepository;
import eu.senla.naumovich.dto.OrderDto;
import eu.senla.naumovich.entities.Order;
import eu.senla.naumovich.exceptions.NoRecords;
import eu.senla.naumovich.mapper.OrderMapper;
import eu.senla.naumovich.services.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
        private final OrderRepository orderRepository;
        private final OrderMapper orderMapper;

        @Override
        public List<OrderDto> getAll() {
                List<Order> orders = orderRepository.getAll();
                if (orders.isEmpty()) {
                        return Collections.emptyList();
                }
                return orderMapper.toDtoList(orders);
        }

        @Override
        public OrderDto getById(Long id) {
                return orderMapper.toDto(orderRepository.findById(id)
                                .orElseThrow(() -> new NoRecords("No record with such ID " + id)));
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
        public void delete(Long id) {
                orderRepository.deleteById(id);
        }
}
