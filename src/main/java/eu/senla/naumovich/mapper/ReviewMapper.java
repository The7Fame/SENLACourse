package eu.senla.naumovich.mapper;

import eu.senla.naumovich.dto.review.ReviewDto;
import eu.senla.naumovich.dto.review.ReviewForBookDto;
import eu.senla.naumovich.entities.Review;
import eu.senla.naumovich.mapper.common.InterfaceMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper extends InterfaceMapper<Review, ReviewDto> {
    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);
    List<ReviewForBookDto> toReviewForBookDtoList(List<Review> entityList);
}
