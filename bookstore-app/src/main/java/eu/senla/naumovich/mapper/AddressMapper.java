package eu.senla.naumovich.mapper;

import eu.senla.naumovich.dto.address.AddressDto;
import eu.senla.naumovich.dto.address.AddressShortDto;
import eu.senla.naumovich.entities.Address;
import eu.senla.naumovich.mapper.common.InterfaceMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddressMapper extends InterfaceMapper<Address, AddressDto, AddressShortDto> {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);
}
