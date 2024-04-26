package eu.senla.naumovich.service;

import eu.senla.naumovich.dao.repository.ReviewRepository;
import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.dto.ReviewDto;
import eu.senla.naumovich.entities.Review;
import eu.senla.naumovich.mapper.ReviewMapper;
import eu.senla.naumovich.services.impl.ReviewServiceImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
        when(repository.getAll()).thenReturn(Collections.emptyList());
        List<ReviewDto> result = service.getAll();
        Assertions.assertTrue(result.isEmpty());
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
        when(repository.update(review)).thenReturn(review);
        when(mapper.toDto(review)).thenReturn(reviewDto);
        ReviewDto result = service.update(reviewDto);
        Assertions.assertEquals(reviewDto, result);
        verify(repository).update(review);
        verify(mapper).toEntity(reviewDto);
        verify(mapper).toDto(review);
    }

    @Test
    public void createTest() {
        Review review = Generator.createReview();
        ReviewDto reviewDto = Generator.createReviewDto();
        when(mapper.toEntity(reviewDto)).thenReturn(review);
        when(repository.create(review)).thenReturn(review);
        when(mapper.toDto(review)).thenReturn(reviewDto);
        ReviewDto result = service.create(reviewDto);
        Assertions.assertEquals(reviewDto, result);
        verify(repository).create(review);
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
