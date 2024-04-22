package eu.senla.naumovich.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.naumovich.dto.ReviewDto;
import eu.senla.naumovich.services.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final ObjectMapper objectMapper;

    public List<String> getAll() {
        List<ReviewDto> reviewsDto = reviewService.getAll();
        List<String> reviewsJSON = reviewsDto.stream().map(this::fromDtoToJSON).collect(Collectors.toList());
        return reviewsJSON;
    }

    public String getById(String reviewJSON) {
        return fromDtoToJSON(reviewService.getById(fromJSONToDto(reviewJSON)));
    }

    public String update(String reviewJSON) {
        return fromDtoToJSON(reviewService.update(fromJSONToDto(reviewJSON)));
    }

    public void create(String reviewJSON) {
        reviewService.create(fromJSONToDto(reviewJSON));
    }

    public void delete(String reviewJSON) {
        reviewService.delete(fromJSONToDto(reviewJSON));
    }

    private ReviewDto fromJSONToDto(String reviewJSON) {
        try {
            return objectMapper.readValue(reviewJSON, ReviewDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String fromDtoToJSON(ReviewDto reviewDto) {
        try {
            return objectMapper.writeValueAsString(reviewDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
