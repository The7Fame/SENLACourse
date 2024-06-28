package eu.senla.naumovich.service;

import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.dto.review.ReviewDto;
import eu.senla.naumovich.dto.review.ReviewShortDto;
import eu.senla.naumovich.entities.Review;
import eu.senla.naumovich.mapper.ReviewMapper;
import eu.senla.naumovich.repositories.ReviewRepository;
import eu.senla.naumovich.services.impl.ReviewServiceImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {
    @Mock
    private ReviewMapper mapper;
    @Mock
    ReviewRepository repository;
    @InjectMocks
    private ReviewServiceImpl service;

    @Test
    public void getAllTest() {
        String sort = "id";
        Pageable pageable = PageRequest.of(0, 5, Sort.by(sort));
        Review review = new Review();
        Page<Review> reviewPage = new PageImpl<>(Collections.singletonList(review));
        when(repository.findAll(pageable)).thenReturn(reviewPage);
        when(mapper.toDtoList(anyList())).thenReturn(Collections.singletonList(ReviewShortDto.builder().build()));
        List<ReviewShortDto> result = service.getAll(1, 5, sort);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(repository).findAll(pageable);
        verify(mapper).toDtoList(anyList());
    }

    @Test
    public void getByIdTest() {
        Review review = Generator.createReview();
        ReviewDto reviewDto = Generator.createReviewDto();
        when(repository.findById(1L)).thenReturn(Optional.ofNullable(review));
        when(mapper.toDto(review)).thenReturn(reviewDto);
        ReviewDto result = service.getById(1L);
        Assertions.assertEquals(reviewDto, result);
        verify(repository).findById(1L);
        verify(mapper).toDto(review);
    }

    @Test
    public void updateTest() {
        Review review = Generator.createReview();
        ReviewDto reviewDto = Generator.createReviewDto();
        when(mapper.toEntity(reviewDto)).thenReturn(review);
        when(repository.save(review)).thenReturn(review);
        when(mapper.toDto(review)).thenReturn(reviewDto);
        ReviewDto result = service.update(reviewDto);
        Assertions.assertEquals(reviewDto, result);
        verify(repository).save(review);
        verify(mapper).toEntity(reviewDto);
        verify(mapper).toDto(review);
    }

    @Test
    public void createTest() {
        Review review = Generator.createReview();
        ReviewDto reviewDto = Generator.createReviewDto();
        when(mapper.toEntity(reviewDto)).thenReturn(review);
        when(repository.save(review)).thenReturn(review);
        when(mapper.toDto(review)).thenReturn(reviewDto);
        ReviewDto result = service.create(reviewDto);
        Assertions.assertEquals(reviewDto, result);
        verify(repository).save(review);
        verify(mapper).toEntity(reviewDto);
        verify(mapper).toDto(review);
    }

    @Test
    public void deleteTest() {
        Review review = Generator.createReview();
        doNothing().when(repository).deleteById(review.getId());
        service.delete(review.getId());
        verify(repository).deleteById(review.getId());
    }
}
