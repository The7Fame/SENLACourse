package eu.senla.naumovich.mapper;

import eu.senla.naumovich.dto.privilege.PrivilegeDto;
import eu.senla.naumovich.entities.Privilege;
import eu.senla.naumovich.mapper.common.InterfaceMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PrivilegeMapper extends InterfaceMapper<Privilege, PrivilegeDto, PrivilegeDto> {
    PrivilegeMapper INSTANCE = Mappers.getMapper(PrivilegeMapper.class);
}
