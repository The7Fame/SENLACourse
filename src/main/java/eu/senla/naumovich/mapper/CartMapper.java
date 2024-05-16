package eu.senla.naumovich.mapper;

import eu.senla.naumovich.dto.cart.CartDto;
import eu.senla.naumovich.entities.Cart;
import eu.senla.naumovich.mapper.common.InterfaceMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CartMapper extends InterfaceMapper<Cart, CartDto> {
    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);
}
