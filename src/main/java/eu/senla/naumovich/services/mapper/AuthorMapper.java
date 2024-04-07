package eu.senla.naumovich.services.mapper;

import eu.senla.naumovich.dto.AuthorDto;
import eu.senla.naumovich.dto.PublisherDto;
import eu.senla.naumovich.entities.Author;
import eu.senla.naumovich.entities.Publisher;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    AuthorDto toDto(Author author);

    Author toEntity(AuthorDto authorDto);
}
