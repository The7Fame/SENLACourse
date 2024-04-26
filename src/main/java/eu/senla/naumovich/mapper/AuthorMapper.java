package eu.senla.naumovich.mapper;

import eu.senla.naumovich.dto.AuthorDto;
import eu.senla.naumovich.entities.Author;
import eu.senla.naumovich.mapper.common.InterfaceMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AuthorMapper extends InterfaceMapper<Author, AuthorDto> {
    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);
}
