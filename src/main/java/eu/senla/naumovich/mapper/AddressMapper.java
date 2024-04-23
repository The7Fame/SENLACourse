package eu.senla.naumovich.mapper;

import eu.senla.naumovich.dto.AddressDto;
import eu.senla.naumovich.entities.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    AddressDto toDto(Address address);

    Address toEntity(AddressDto addressDto);
}
