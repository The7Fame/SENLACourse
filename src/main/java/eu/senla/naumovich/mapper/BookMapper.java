package eu.senla.naumovich.mapper;

import eu.senla.naumovich.dto.BookDto;
import eu.senla.naumovich.entities.Book;
import eu.senla.naumovich.mapper.common.InterfaceMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookMapper extends InterfaceMapper<Book, BookDto> {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);
}
