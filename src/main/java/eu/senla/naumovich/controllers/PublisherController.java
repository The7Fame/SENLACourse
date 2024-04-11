package eu.senla.naumovich.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.naumovich.dto.PublisherDto;
import eu.senla.naumovich.services.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PublisherController {
    private final PublisherService publisherService;
    private final ObjectMapper objectMapper;

    public List<String> getAll() {
        List<PublisherDto> publishersDto = publisherService.getAll();
        List<String> publishersJSON = publishersDto.stream().map(this::fromDtoToJSON).collect(Collectors.toList());
        return publishersJSON;
    }

    public String getById(String publisherJSON) {
        return fromDtoToJSON(publisherService.getById(fromJSONToDto(publisherJSON)));
    }

    public String update(String publisherJSON) {
        return fromDtoToJSON(publisherService.update(fromJSONToDto(publisherJSON)));
    }

    public String create(String publisherJSON) {
        return fromDtoToJSON(publisherService.create(fromJSONToDto(publisherJSON)));
    }

    public void delete(String addressJSON) {
        publisherService.delete(fromJSONToDto(addressJSON));
    }

    public synchronized String testMethod(){
        try {
            return objectMapper.writeValueAsString(publisherService.testMethod());
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }

    private PublisherDto fromJSONToDto(String publisherJSON) {
        try {
            return objectMapper.readValue(publisherJSON, PublisherDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String fromDtoToJSON(PublisherDto publisherDto) {
        try {
            return objectMapper.writeValueAsString(publisherDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
