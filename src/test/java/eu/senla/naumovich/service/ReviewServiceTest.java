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

import java.util.ArrayList;
import java.util.List;

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
        Review review = Generator.createReview();
        ReviewDto reviewDto = Generator.createReviewDto();
        List<ReviewDto> reviewDtos = new ArrayList<>();
        List<Review> reviews = new ArrayList<>();
        reviewDtos.add(reviewDto);
        reviews.add(review);
        when(repository.getAll()).thenReturn(reviews);
        when(mapper.toDto(review)).thenReturn(reviewDto);
        List<ReviewDto> result = service.getAll();
        Assertions.assertEquals(reviewDtos, result);
        verify(repository).getAll();
    }

    @Test
    public void getByIdTest() {
        Review review = Generator.createReview();
        ReviewDto reviewDto = Generator.createReviewDto();
        when(repository.getById(1L)).thenReturn(review);
        when(mapper.toDto(review)).thenReturn(reviewDto);
        ReviewDto result = service.getById(1L);
        Assertions.assertEquals(reviewDto, result);
        verify(repository).getById(1L);
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
        doNothing().when(repository).create(review);
        service.create(reviewDto);
        verify(repository).create(review);
        verify(mapper).toEntity(reviewDto);
    }

    @Test
    public void deleteTest() {
        Review review = Generator.createReview();
        when(repository.getById(review.getId())).thenReturn(review);
        doNothing().when(repository).delete(review);
        service.delete(review.getId());
        verify(repository).getById(review.getId());
        verify(repository).delete(review);
    }
}
