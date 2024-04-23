package eu.senla.naumovich.mapper;

import eu.senla.naumovich.dto.PublisherDto;
import eu.senla.naumovich.entities.Publisher;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PublisherMapper {

    PublisherMapper INSTANCE = Mappers.getMapper(PublisherMapper.class);

    PublisherDto toDto(Publisher publisher);

    Publisher toEntity(PublisherDto publisherDto);
}
