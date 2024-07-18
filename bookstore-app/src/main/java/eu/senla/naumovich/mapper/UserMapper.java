package eu.senla.naumovich.mapper;

import eu.senla.naumovich.dto.user.UserDto;
import eu.senla.naumovich.dto.user.UserShortDto;
import eu.senla.naumovich.entities.User;
import eu.senla.naumovich.mapper.common.InterfaceMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends InterfaceMapper<User, UserDto, UserShortDto> {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
}
