package eu.senla.naumovich.services.mapper;

import eu.senla.naumovich.dto.CartDto;
import eu.senla.naumovich.entities.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CartMapper {
    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    CartDto toDto(Cart cart);

    Cart toEntity(CartDto cartDto);
}
