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


@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ReviewDto> getAll() {
        List<ReviewDto> reviewsDto = new ArrayList<>();
        List<Review> reviews = reviewRepository.getAll();
        for(Review review : reviews){
            reviewsDto.add(modelMapper.map(review, ReviewDto.class));
        }
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
