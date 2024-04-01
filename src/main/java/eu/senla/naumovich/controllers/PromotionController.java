package eu.senla.naumovich.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.naumovich.dto.PromotionDto;
import eu.senla.naumovich.services.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PromotionController {
    private final PromotionService promotionService;
    private final ObjectMapper objectMapper;

    public List<String> getAll() throws JsonProcessingException {
        List<PromotionDto> promotionsDto = promotionService.getAll();
        List<String> promotionsJSON = new ArrayList<>();
        for(PromotionDto promotionDto : promotionsDto){
            promotionsJSON.add(fromDtoToJSON(promotionDto));
        }
        return promotionsJSON;
    }

    public String getById(String promotionJSON) throws JsonProcessingException {
        return fromDtoToJSON(promotionService.getById(fromJSONToDto(promotionJSON)));
    }

    public String update(String promotionJSON) throws JsonProcessingException {
        return fromDtoToJSON(promotionService.update(fromJSONToDto(promotionJSON)));
    }

    public String create(String promotionJSON) throws JsonProcessingException {
        return fromDtoToJSON(promotionService.create(fromJSONToDto(promotionJSON)));
    }

    public void delete(String promotionJSON) throws JsonProcessingException {
        promotionService.delete(fromJSONToDto(promotionJSON));
    }
    private PromotionDto fromJSONToDto(String promotionJSON) throws JsonProcessingException {
        return objectMapper.readValue(promotionJSON, PromotionDto.class);
    }

    private String fromDtoToJSON(PromotionDto promotionDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(promotionDto);
    }
}
