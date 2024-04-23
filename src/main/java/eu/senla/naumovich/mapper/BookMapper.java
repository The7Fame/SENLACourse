package eu.senla.naumovich.mapper;

import eu.senla.naumovich.dto.BookDto;
import eu.senla.naumovich.entities.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDto toDto(Book book);

    Book toEntity(BookDto bookDto);
}
