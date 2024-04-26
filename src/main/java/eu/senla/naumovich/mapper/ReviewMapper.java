package eu.senla.naumovich.mapper;

import eu.senla.naumovich.dto.ReviewDto;
import eu.senla.naumovich.entities.Review;
import eu.senla.naumovich.mapper.common.InterfaceMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReviewMapper extends InterfaceMapper<Review, ReviewDto> {
    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);
}
