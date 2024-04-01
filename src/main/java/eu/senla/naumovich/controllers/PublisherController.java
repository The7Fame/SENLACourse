package eu.senla.naumovich.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.naumovich.dto.PublisherDto;
import eu.senla.naumovich.services.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PublisherController {
    private final PublisherService publisherService;
    private final ObjectMapper objectMapper;

    public List<String> getAll() throws JsonProcessingException {
        List<PublisherDto> publishersDto = publisherService.getAll();
        List<String> publishersJSON = new ArrayList<>();
        for(PublisherDto publisherDto : publishersDto){
            publishersJSON.add(fromDtoToJSON(publisherDto));
        }
        return publishersJSON;
    }

    public String getById(String publisherJSON) throws JsonProcessingException {
        return fromDtoToJSON(publisherService.getById(fromJSONToDto(publisherJSON)));
    }

    public String update(String publisherJSON) throws JsonProcessingException {
        return fromDtoToJSON(publisherService.update(fromJSONToDto(publisherJSON)));
    }

    public String create(String publisherJSON) throws JsonProcessingException {
        return fromDtoToJSON(publisherService.create(fromJSONToDto(publisherJSON)));
    }

    public void delete(String addressJSON) throws JsonProcessingException {
        publisherService.delete(fromJSONToDto(addressJSON));
    }
    private PublisherDto fromJSONToDto(String publisherJSON) throws JsonProcessingException {
        return objectMapper.readValue(publisherJSON, PublisherDto.class);
    }

    private String fromDtoToJSON(PublisherDto publisherDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(publisherDto);
    }
}
