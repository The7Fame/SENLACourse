package eu.senla.naumovich.mapper;

import eu.senla.naumovich.dto.order.OrderDto;
import eu.senla.naumovich.dto.order.OrderShortDto;
import eu.senla.naumovich.entities.Order;
import eu.senla.naumovich.mapper.common.InterfaceMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapper extends InterfaceMapper<Order, OrderDto, OrderShortDto> {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

}
