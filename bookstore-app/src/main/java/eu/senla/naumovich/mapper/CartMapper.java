package eu.senla.naumovich.mapper;

import eu.senla.naumovich.dto.cart.CartDto;
import eu.senla.naumovich.dto.cart.CartShortDto;
import eu.senla.naumovich.entities.Cart;
import eu.senla.naumovich.mapper.common.InterfaceMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CartMapper extends InterfaceMapper<Cart, CartDto, CartShortDto> {
    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);
}
