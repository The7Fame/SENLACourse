package eu.senla.naumovich.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.naumovich.dto.PromotionDto;
import eu.senla.naumovich.services.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PromotionController {
    private final PromotionService promotionService;
    private final ObjectMapper objectMapper;

    public List<String> getAll() {
        List<PromotionDto> promotionsDto = promotionService.getAll();
        List<String> promotionsJSON = promotionsDto.stream().map(this::fromDtoToJSON).collect(Collectors.toList());
        return promotionsJSON;
    }

    public String getById(String promotionJSON){
        return fromDtoToJSON(promotionService.getById(fromJSONToDto(promotionJSON)));
    }

    public String update(String promotionJSON){
        return fromDtoToJSON(promotionService.update(fromJSONToDto(promotionJSON)));
    }

    public String create(String promotionJSON){
        return fromDtoToJSON(promotionService.create(fromJSONToDto(promotionJSON)));
    }

    public void delete(String promotionJSON){
        promotionService.delete(fromJSONToDto(promotionJSON));
    }
    private PromotionDto fromJSONToDto(String promotionJSON) {
        try {
            return objectMapper.readValue(promotionJSON, PromotionDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String fromDtoToJSON(PromotionDto promotionDto) {
        try {
            return objectMapper.writeValueAsString(promotionDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
