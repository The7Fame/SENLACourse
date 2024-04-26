package eu.senla.naumovich.mapper;

import eu.senla.naumovich.dto.AddressDto;
import eu.senla.naumovich.entities.Address;
import eu.senla.naumovich.mapper.common.InterfaceMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AddressMapper extends InterfaceMapper<Address, AddressDto> {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);
}
