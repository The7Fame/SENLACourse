package eu.senla.naumovich.services.mapper;

import eu.senla.naumovich.dto.PublisherDto;
import eu.senla.naumovich.dto.ReviewDto;
import eu.senla.naumovich.entities.Publisher;
import eu.senla.naumovich.entities.Review;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    ReviewDto toDto(Review review);

    Review toEntity(ReviewDto reviewDto);
}
