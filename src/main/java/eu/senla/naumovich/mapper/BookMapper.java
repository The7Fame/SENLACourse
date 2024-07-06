package eu.senla.naumovich.mapper;

import eu.senla.naumovich.dto.book.BookDto;
import eu.senla.naumovich.dto.book.BookShortDto;
import eu.senla.naumovich.entities.Book;
import eu.senla.naumovich.mapper.common.InterfaceMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookMapper extends InterfaceMapper<Book, BookDto, BookShortDto> {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);
}
