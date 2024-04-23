package eu.senla.naumovich.services.impl;

import eu.senla.naumovich.dao.repository.ReviewRepository;
import eu.senla.naumovich.dto.ReviewDto;
import eu.senla.naumovich.entities.Review;
import eu.senla.naumovich.exceptions.NoRecords;
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
        try {
            List<Review> reviews = reviewRepository.getAll();
            List<ReviewDto> reviewsDto = reviews.stream()
                    .map(reviewMapper::toDto)
                    .collect(Collectors.toList());
            return reviewsDto;
        } catch (Exception e) {
            throw new NoRecords("No records");
        }
    }

    @Override
    public ReviewDto getById(Long id) {
        try {
            return reviewMapper.toDto(reviewRepository.getById(id));
        } catch (Exception e) {
            throw new NoRecords("No record with such ID " + id);
        }
    }

    @Override
    public ReviewDto update(ReviewDto review) {
        try {
            return reviewMapper.toDto(reviewRepository.update(reviewMapper.toEntity(review)));
        } catch (Exception e) {
            throw new NoRecords("No record with such ID " + review.getId());
        }
    }

    @Override
    public void create(ReviewDto review) {
        try {
            reviewRepository.create(reviewMapper.toEntity(review));
        } catch (Exception e) {
            throw new NoRecords("Cannot create record");
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Review review = reviewRepository.getById(id);
            reviewRepository.delete(review);
        } catch (Exception e) {
            throw new NoRecords("No record with such ID " + id);
        }
    }
}
