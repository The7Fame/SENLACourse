package eu.senla.naumovich.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.naumovich.dto.ReviewDto;
import eu.senla.naumovich.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final ObjectMapper objectMapper;

    public List<String> getAll() throws JsonProcessingException {
        List<ReviewDto> reviewsDto = reviewService.getAll();
        List<String> reviewsJSON = new ArrayList<>();
        for(ReviewDto reviewDto : reviewsDto){
            reviewsJSON.add(fromDtoToJSON(reviewDto));
        }
        return reviewsJSON;
    }

    public String getById(String reviewJSON) throws JsonProcessingException {
        return fromDtoToJSON(reviewService.getById(fromJSONToDto(reviewJSON)));
    }

    public String update(String reviewJSON) throws JsonProcessingException {
        return fromDtoToJSON(reviewService.update(fromJSONToDto(reviewJSON)));
    }

    public String create(String reviewJSON) throws JsonProcessingException {
        return fromDtoToJSON(reviewService.create(fromJSONToDto(reviewJSON)));
    }

    public void delete(String reviewJSON) throws JsonProcessingException {
        reviewService.delete(fromJSONToDto(reviewJSON));
    }
    private ReviewDto fromJSONToDto(String reviewJSON) throws JsonProcessingException {
        return objectMapper.readValue(reviewJSON, ReviewDto.class);
    }

    private String fromDtoToJSON(ReviewDto reviewDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(reviewDto);
    }
}
