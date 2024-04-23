package eu.senla.naumovich.mapper;

import eu.senla.naumovich.dto.ReviewDto;
import eu.senla.naumovich.entities.Review;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    ReviewDto toDto(Review review);

    Review toEntity(ReviewDto reviewDto);
}
