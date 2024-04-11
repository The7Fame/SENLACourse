package eu.senla.naumovich.services.mapper;

import eu.senla.naumovich.dto.PaymentDto;
import eu.senla.naumovich.dto.PrivilegeDto;
import eu.senla.naumovich.dto.PublisherDto;
import eu.senla.naumovich.entities.Payment;
import eu.senla.naumovich.entities.Privilege;
import eu.senla.naumovich.entities.Publisher;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PrivilegeMapper {
    PrivilegeMapper INSTANCE = Mappers.getMapper(PrivilegeMapper.class);

    PrivilegeDto toDto(Privilege privilege);

    Privilege toEntity(PrivilegeDto privilegeDto);
}
