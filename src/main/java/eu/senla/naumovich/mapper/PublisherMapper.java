package eu.senla.naumovich.mapper;

import eu.senla.naumovich.dto.PublisherDto;
import eu.senla.naumovich.entities.Publisher;
import eu.senla.naumovich.mapper.common.InterfaceMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PublisherMapper extends InterfaceMapper<Publisher, PublisherDto> {

    PublisherMapper INSTANCE = Mappers.getMapper(PublisherMapper.class);
}
