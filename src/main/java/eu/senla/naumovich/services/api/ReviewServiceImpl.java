package eu.senla.naumovich.services.api;

import eu.senla.naumovich.dao.repository.ReviewRepository;
import eu.senla.naumovich.dto.ReviewDto;
import eu.senla.naumovich.entities.Review;
import eu.senla.naumovich.services.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ReviewDto> getAll() {
        List<Review> reviews = reviewRepository.getAll();
        List<ReviewDto> reviewsDto = reviews.stream()
                .map(review -> modelMapper.map(review, ReviewDto.class))
                .collect(Collectors.toList());
        return reviewsDto;
    }

    @Override
    public ReviewDto getById(ReviewDto review) {
        return modelMapper.map(reviewRepository.getById(modelMapper.map(review, Review.class)), ReviewDto.class);
    }

    @Override
    public ReviewDto update(ReviewDto review) {
        return modelMapper.map(reviewRepository.update(modelMapper.map(review, Review.class)), ReviewDto.class);
    }

    @Override
    public ReviewDto create(ReviewDto review) {
        return modelMapper.map(reviewRepository.create(modelMapper.map(review, Review.class)), ReviewDto.class);
    }

    @Override
    public void delete(ReviewDto review) {
        reviewRepository.delete(modelMapper.map(review, Review.class));
    }
}
