package eu.senla.naumovich.services.mapper;

import eu.senla.naumovich.dto.PublisherDto;
import eu.senla.naumovich.dto.UserDto;
import eu.senla.naumovich.entities.Publisher;
import eu.senla.naumovich.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto(User user);

    User toEntity(UserDto userDto);
}
