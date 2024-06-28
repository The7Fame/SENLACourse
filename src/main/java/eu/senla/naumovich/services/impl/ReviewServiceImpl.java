package eu.senla.naumovich.services.impl;

import eu.senla.naumovich.dto.book.BookDto;
import eu.senla.naumovich.dto.review.ReviewCreateDto;
import eu.senla.naumovich.dto.review.ReviewDto;
import eu.senla.naumovich.dto.review.ReviewShortDto;
import eu.senla.naumovich.dto.user.UserDto;
import eu.senla.naumovich.entities.Review;
import eu.senla.naumovich.exceptions.NoRecordException;
import eu.senla.naumovich.mapper.ReviewMapper;
import eu.senla.naumovich.repositories.ReviewRepository;
import eu.senla.naumovich.security.SecurityUser;
import eu.senla.naumovich.services.service.BookService;
import eu.senla.naumovich.services.service.ReviewService;
import eu.senla.naumovich.services.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
        private final ReviewRepository reviewRepository;
        private final UserService userService;
        private final BookService bookService;
        private final ReviewMapper reviewMapper;

        @Override
        public List<ReviewShortDto> getAll(int page, int size, String sort) {
                Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sort));
                Page<Review> reviewPage = reviewRepository.findAll(pageable);
                return reviewMapper.toDtoList(reviewPage.getContent());
        }

        @Override
        public ReviewDto getById(Long id) {
                return reviewMapper.toDto(reviewRepository.findById(id)
                                .orElseThrow(() -> new NoRecordException("No record with such ID " + id)));
        }

        @Override
        public ReviewDto update(ReviewDto review) {
                return reviewMapper.toDto(reviewRepository.save(reviewMapper.toEntity(review)));
        }

        @Override
        public ReviewDto create(ReviewDto review) {
                return reviewMapper.toDto(reviewRepository.save(reviewMapper.toEntity(review)));
        }

        @Override
        public void delete(Long id) {
                reviewRepository.deleteById(id);
        }

        @Transactional
        public ReviewDto createReviewByUser(SecurityUser securityUser, Long bookId, ReviewCreateDto reviewCreateDto) {
                UserDto userDto = userService.getById(securityUser.getId());
                BookDto bookDto = bookService.getById(bookId);
                ReviewDto reviewDto = ReviewDto.builder()
                        .user(userDto)
                        .book(bookDto)
                        .text(reviewCreateDto.getText())
                        .rating(reviewCreateDto.getRating())
                        .build();
                return create(reviewDto);
        }

        @Transactional
        public void deleteUserReviewById(SecurityUser securityUser, Long reviewId) {
                reviewRepository.getByUserAndReviewById(securityUser.getId(), reviewId).orElseThrow(() -> new NoRecordException("No record with such ID " + reviewId));
                delete(reviewId);
        }
}
