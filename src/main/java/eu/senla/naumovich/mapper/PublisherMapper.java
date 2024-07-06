package eu.senla.naumovich.mapper;

import eu.senla.naumovich.dto.publisher.PublisherDto;
import eu.senla.naumovich.entities.Publisher;
import eu.senla.naumovich.mapper.common.InterfaceMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PublisherMapper extends InterfaceMapper<Publisher, PublisherDto, PublisherDto> {

    PublisherMapper INSTANCE = Mappers.getMapper(PublisherMapper.class);
}
