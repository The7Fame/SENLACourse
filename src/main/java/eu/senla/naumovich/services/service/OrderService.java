package eu.senla.naumovich.services.service;

import eu.senla.naumovich.dto.OrderDto;
import eu.senla.naumovich.services.service.common.AbstractService;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService extends AbstractService<OrderDto> {
    List<OrderDto> filterOrderByPrice(BigDecimal totalPrice);
}
