package eu.senla.naumovich.services.mapper;

import eu.senla.naumovich.dto.PublisherDto;
import eu.senla.naumovich.dto.RoleDto;
import eu.senla.naumovich.entities.Publisher;
import eu.senla.naumovich.entities.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleDto toDto(Role role);

    Role toEntity(RoleDto roleDto);
}
