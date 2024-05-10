package eu.senla.naumovich.services.impl;

import eu.senla.naumovich.dao.repository.ReviewRepository;
import eu.senla.naumovich.dto.ReviewDto;
import eu.senla.naumovich.entities.Review;
import eu.senla.naumovich.exceptions.NoRecords;
import eu.senla.naumovich.mapper.ReviewMapper;
import eu.senla.naumovich.services.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
        private final ReviewRepository reviewRepository;
        private final ReviewMapper reviewMapper;

        @Override
        public List<ReviewDto> getAll(int size, int page) {
                List<Review> reviews = reviewRepository.getAll(size, page);
                return reviewMapper.toDtoList(reviews);
        }

        @Override
        public ReviewDto getById(Long id) {

                return reviewMapper.toDto(reviewRepository.findById(id)
                                .orElseThrow(() -> new NoRecords("No record with such ID " + id)));

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
        public void delete(Long id) {
                reviewRepository.deleteById(id);

        }
}
