package eu.senla.naumovich.mapper;

import eu.senla.naumovich.dto.AuthorDto;
import eu.senla.naumovich.entities.Author;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    AuthorDto toDto(Author author);

    Author toEntity(AuthorDto authorDto);
}
