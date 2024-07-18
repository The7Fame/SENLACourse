package eu.senla.naumovich.mapper;

import eu.senla.naumovich.dto.author.AuthorDto;
import eu.senla.naumovich.dto.author.AuthorShortDto;
import eu.senla.naumovich.entities.Author;
import eu.senla.naumovich.mapper.common.InterfaceMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthorMapper extends InterfaceMapper<Author, AuthorDto, AuthorShortDto> {
    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);
}
