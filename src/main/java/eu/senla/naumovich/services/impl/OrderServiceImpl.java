package eu.senla.naumovich.services.impl;

import eu.senla.naumovich.dao.repository.OrderRepository;
import eu.senla.naumovich.dao.repository.UserRepository;
import eu.senla.naumovich.dto.book.BookDto;
import eu.senla.naumovich.dto.order.OrderDto;
import eu.senla.naumovich.dto.user.UserDto;
import eu.senla.naumovich.entities.Book;
import eu.senla.naumovich.entities.Order;
import eu.senla.naumovich.entities.Promotion;
import eu.senla.naumovich.exceptions.NoRecords;
import eu.senla.naumovich.mapper.BookMapper;
import eu.senla.naumovich.mapper.OrderMapper;
import eu.senla.naumovich.security.SecurityUser;
import eu.senla.naumovich.services.service.OrderService;
import eu.senla.naumovich.services.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
        private final OrderRepository orderRepository;
        private final UserService userService;
        private final OrderMapper orderMapper;
        private final BookMapper bookMapper;

        @Override
        public List<OrderDto> getAll(int size, int page) {
                List<Order> orders = orderRepository.getAll(size, page);
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

        @Override
        public List<OrderDto> filterOrderByPrice(BigDecimal totalPrice) {
                return List.of();
        }

        @Transactional
        public OrderDto createOrderFromBooks(SecurityUser securityUser, List<BookDto> books) {
                BigDecimal totalPrice = calculateTotalPrice(books);
                UserDto user = userService.getById(securityUser.getId());
                OrderDto orderDto = OrderDto.builder()
                        .orderDate(new Date())
                        .user(user)
                        .totalPrice(totalPrice).build();
                return create(orderDto);
        }

        public BigDecimal calculateTotalPrice(List<BookDto> books) {
                BigDecimal totalPrice = BigDecimal.ZERO;
                for(Book book : bookMapper.toEntityList(books)){
                        BigDecimal bookPrice = book.getPrice();
                        Optional<List<Promotion>> promotions = Optional.ofNullable(book.getPromotions());
                        if(promotions.isPresent()) {
                                BigDecimal maxPercent = promotions.get().stream().map(Promotion::getPercent).max(BigDecimal::compareTo).orElse(BigDecimal.ZERO);
                                BigDecimal promotionAmount = bookPrice.multiply(maxPercent).divide(BigDecimal.valueOf(100));
                                bookPrice = bookPrice.subtract(promotionAmount);
                        }
                        totalPrice = totalPrice.add(bookPrice);
                }
                return totalPrice;
        }

        @Override
        public List<OrderDto> getUserOrders(SecurityUser securityUser, int page, int size) {
                return orderMapper.toDtoList(orderRepository.getOrdersByUserId(securityUser.getId(), page, size));
        }

        @Override
        public OrderDto getUserOrderById(SecurityUser securityUser, int orderId) {
                return orderMapper.toDto(orderRepository.getByUserAndOrderById(securityUser.getId(), orderId).orElseThrow(() -> new NoRecords("No record with such ID " + orderId)));
        }
}
