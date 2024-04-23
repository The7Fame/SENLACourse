package eu.senla.naumovich.mapper;

import eu.senla.naumovich.dto.PrivilegeDto;
import eu.senla.naumovich.entities.Privilege;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PrivilegeMapper {
    PrivilegeMapper INSTANCE = Mappers.getMapper(PrivilegeMapper.class);

    PrivilegeDto toDto(Privilege privilege);

    Privilege toEntity(PrivilegeDto privilegeDto);
}
