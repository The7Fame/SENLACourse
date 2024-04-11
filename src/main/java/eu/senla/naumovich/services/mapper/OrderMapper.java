package eu.senla.naumovich.services.mapper;

import eu.senla.naumovich.dto.OrderDto;
import eu.senla.naumovich.dto.PublisherDto;
import eu.senla.naumovich.entities.Order;
import eu.senla.naumovich.entities.Publisher;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDto toDto(Order order);

    Order toEntity(OrderDto orderDto);
}
