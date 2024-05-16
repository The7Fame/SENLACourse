package eu.senla.naumovich.mapper;

import eu.senla.naumovich.dto.privilege.PrivilegeDto;
import eu.senla.naumovich.entities.Privilege;
import eu.senla.naumovich.mapper.common.InterfaceMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PrivilegeMapper extends InterfaceMapper<Privilege, PrivilegeDto> {
    PrivilegeMapper INSTANCE = Mappers.getMapper(PrivilegeMapper.class);
}
