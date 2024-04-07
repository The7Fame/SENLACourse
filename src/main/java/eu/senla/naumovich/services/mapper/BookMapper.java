package eu.senla.naumovich.services.mapper;

import eu.senla.naumovich.dto.BookDto;
import eu.senla.naumovich.dto.PublisherDto;
import eu.senla.naumovich.entities.Book;
import eu.senla.naumovich.entities.Publisher;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDto toDto(Book book);

    Book toEntity(BookDto bookDto);
}
