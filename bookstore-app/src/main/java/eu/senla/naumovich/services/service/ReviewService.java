package eu.senla.naumovich.services.service;

import eu.senla.naumovich.dto.review.ReviewCreateDto;
import eu.senla.naumovich.dto.review.ReviewDto;
import eu.senla.naumovich.dto.review.ReviewShortDto;
import eu.senla.naumovich.security.SecurityUser;
import eu.senla.naumovich.services.service.common.AbstractService;

public interface ReviewService extends AbstractService<ReviewDto, ReviewShortDto> {
    public ReviewDto createReviewByUser(SecurityUser securityUser, Long bookId, ReviewCreateDto reviewCreateDto);

    public void deleteUserReviewById(SecurityUser securityUser, Long reviewId);
}
