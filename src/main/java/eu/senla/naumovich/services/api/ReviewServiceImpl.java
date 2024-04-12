package eu.senla.naumovich.services.api;

import eu.senla.naumovich.dao.repository.ReviewRepository;
import eu.senla.naumovich.dto.ReviewDto;
import eu.senla.naumovich.entities.Review;
import eu.senla.naumovich.services.mapper.ReviewMapper;
import eu.senla.naumovich.services.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    @Override
    public List<ReviewDto> getAll() {
        List<Review> reviews = reviewRepository.getAll();
        List<ReviewDto> reviewsDto = reviews.stream()
                .map(reviewMapper::toDto)
                .collect(Collectors.toList());
        return reviewsDto;
    }

    @Override
    public ReviewDto getById(ReviewDto review) {
        return reviewMapper.toDto(reviewRepository.getById(reviewMapper.toEntity(review)));
    }

    @Override
    public ReviewDto update(ReviewDto review) {
        return reviewMapper.toDto(reviewRepository.update(reviewMapper.toEntity(review)));
    }

    @Override
    public ReviewDto create(ReviewDto review) {
        return reviewMapper.toDto(reviewRepository.create(reviewMapper.toEntity(review)));
    }

    @Override
    public void delete(ReviewDto review) {
        reviewRepository.delete(reviewMapper.toEntity(review));
    }
}
