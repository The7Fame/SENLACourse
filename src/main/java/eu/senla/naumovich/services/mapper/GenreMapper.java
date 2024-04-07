package eu.senla.naumovich.services.mapper;

import eu.senla.naumovich.dto.GenreDto;
import eu.senla.naumovich.dto.PublisherDto;
import eu.senla.naumovich.entities.Genre;
import eu.senla.naumovich.entities.Publisher;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    GenreMapper INSTANCE = Mappers.getMapper(GenreMapper.class);

    GenreDto toDto(Genre genre);

    Genre toEntity(GenreDto genreDto);
}
