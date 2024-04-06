package eu.senla.naumovich.services.service;

import eu.senla.naumovich.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    List<ReviewDto> getAll();

    ReviewDto getById(ReviewDto review);

    ReviewDto update(ReviewDto review);

    ReviewDto create(ReviewDto review);

    void delete(ReviewDto review);
}
