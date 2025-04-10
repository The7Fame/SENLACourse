package eu.senla.naumovich.mapper;

import eu.senla.naumovich.dto.role.RoleDto;
import eu.senla.naumovich.dto.role.RoleShortDto;
import eu.senla.naumovich.entities.Role;
import eu.senla.naumovich.mapper.common.InterfaceMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper extends InterfaceMapper<Role, RoleDto, RoleShortDto> {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);
}
