package eu.senla.naumovich.services.impl;

import eu.senla.naumovich.dto.book.BookShortDto;
import eu.senla.naumovich.dto.order.OrderDto;
import eu.senla.naumovich.dto.order.OrderShortDto;
import eu.senla.naumovich.dto.user.UserDto;
import eu.senla.naumovich.entities.Book;
import eu.senla.naumovich.entities.Order;
import eu.senla.naumovich.entities.Promotion;
import eu.senla.naumovich.exceptions.NoRecordException;
import eu.senla.naumovich.mapper.BookMapper;
import eu.senla.naumovich.mapper.OrderMapper;
import eu.senla.naumovich.repositories.OrderRepository;
import eu.senla.naumovich.security.SecurityUser;
import eu.senla.naumovich.services.service.OrderService;
import eu.senla.naumovich.services.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        public List<OrderShortDto> getAll(int page, int size, String sort) {
                Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sort));
                Page<Order> orderPage = orderRepository.findAll(pageable);
                return orderMapper.toDtoList(orderPage.getContent());
        }

        @Override
        public OrderDto getById(Long id) {
                return orderMapper.toDto(orderRepository.findById(id)
                                .orElseThrow(() -> new NoRecordException("No record with such ID " + id)));
        }

        @Override
        public OrderDto update(OrderDto order) {
                return orderMapper.toDto(orderRepository.save(orderMapper.toEntity(order)));
        }

        @Override
        public OrderDto create(OrderDto order) {
                return orderMapper.toDto(orderRepository.save(orderMapper.toEntity(order)));
        }

        @Override
        public void delete(Long id) {
                orderRepository.deleteById(id);
        }

        @Transactional
        public OrderDto createOrderFromBooks(SecurityUser securityUser, List<BookShortDto> books) {
                BigDecimal totalPrice = calculateTotalPrice(books);
                UserDto user = userService.getById(securityUser.getId());
                OrderDto orderDto = OrderDto.builder()
                        .orderDate(new Date())
                        .user(user)
                        .totalPrice(totalPrice).build();
                return create(orderDto);
        }

        @Override
        public BigDecimal calculateTotalPrice(List<BookShortDto> books) {
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
        public List<OrderShortDto> getUserOrders(SecurityUser securityUser, int page, int size) {
                Pageable pageable = PageRequest.of(page - 1, size);
                return orderMapper.toDtoList(orderRepository.getOrdersByUserId(securityUser.getId(), pageable).getContent());
        }

        @Override
        public OrderDto getUserOrderById(SecurityUser securityUser, Long orderId) {
                return orderMapper.toDto(orderRepository.getByUserAndOrderById(securityUser.getId(), orderId).orElseThrow(() -> new NoRecordException("No record with such ID " + orderId)));
        }

        @Override
        public void deleteUserOrderById(SecurityUser securityUser, Long orderId) {
                getUserOrderById(securityUser, orderId);
                delete(orderId);
        }
}
