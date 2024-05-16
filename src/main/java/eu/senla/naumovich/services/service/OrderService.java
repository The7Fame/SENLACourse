package eu.senla.naumovich.services.service;

import eu.senla.naumovich.dto.book.BookDto;
import eu.senla.naumovich.dto.order.OrderDto;
import eu.senla.naumovich.entities.Book;
import eu.senla.naumovich.security.SecurityUser;
import eu.senla.naumovich.services.service.common.AbstractService;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService extends AbstractService<OrderDto> {
    List<OrderDto> filterOrderByPrice(BigDecimal totalPrice);
    OrderDto createOrderFromBooks(SecurityUser securityUser, List<BookDto> books);
    BigDecimal calculateTotalPrice(List<BookDto> books);
    List<OrderDto> getUserOrders(SecurityUser securityUser, int page, int size);
    OrderDto getUserOrderById(SecurityUser securityUser, int orderId);
}
