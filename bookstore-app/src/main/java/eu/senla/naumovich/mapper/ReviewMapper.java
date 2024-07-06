package eu.senla.naumovich.mapper;

import eu.senla.naumovich.dto.review.ReviewDto;
import eu.senla.naumovich.dto.review.ReviewForBookDto;
import eu.senla.naumovich.dto.review.ReviewShortDto;
import eu.senla.naumovich.entities.Review;
import eu.senla.naumovich.mapper.common.InterfaceMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReviewMapper extends InterfaceMapper<Review, ReviewDto, ReviewShortDto> {
    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);
    List<ReviewForBookDto> toReviewForBookDtoList(List<Review> entityList);
}
