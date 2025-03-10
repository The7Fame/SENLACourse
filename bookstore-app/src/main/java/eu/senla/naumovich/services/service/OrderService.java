package eu.senla.naumovich.services.service;

import eu.senla.naumovich.dto.book.BookShortDto;
import eu.senla.naumovich.dto.order.OrderDto;
import eu.senla.naumovich.dto.order.OrderShortDto;
import eu.senla.naumovich.dto.order.OrderWithPaymentDto;
import eu.senla.naumovich.security.SecurityUser;
import eu.senla.naumovich.services.service.common.AbstractService;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService extends AbstractService<OrderDto, OrderWithPaymentDto> {

    OrderDto createOrderFromBooks(SecurityUser securityUser, List<BookShortDto> books);

    BigDecimal calculateTotalPrice(List<BookShortDto> books);

    List<OrderShortDto> getUserOrders(SecurityUser securityUser, int page, int size);

    OrderDto getUserOrderById(SecurityUser securityUser, Long orderId);

    void deleteUserOrderById(SecurityUser securityUser, Long orderId);

    List<OrderShortDto> getGreaterThan(BigDecimal totalPrice);
}
