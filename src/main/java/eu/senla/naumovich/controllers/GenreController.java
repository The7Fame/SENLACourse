package eu.senla.naumovich.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.naumovich.dto.GenreDto;
import eu.senla.naumovich.services.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GenreController {
    private final GenreService genreService;
    private final ObjectMapper objectMapper;


    public List<String> getAll() {
        List<GenreDto> genresDto = genreService.getAll();
        List<String> genresJSON = genresDto.stream().map(this::fromDtoToJSON).collect(Collectors.toList());
        return genresJSON;
    }

    public String getById(String genreJSON){
        return fromDtoToJSON(genreService.getById(fromJSONToDto(genreJSON)));
    }

    public String update(String genreJSON){
        return fromDtoToJSON(genreService.update(fromJSONToDto(genreJSON)));
    }

    public String create(String genreJSON){
        return fromDtoToJSON(genreService.create(fromJSONToDto(genreJSON)));
    }

    public void delete(String genreJSON){
        genreService.delete(fromJSONToDto(genreJSON));
    }
    private GenreDto fromJSONToDto(String genreJSON) {
        try {
            return objectMapper.readValue(genreJSON, GenreDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String fromDtoToJSON(GenreDto genreDto) {
        try {
            return objectMapper.writeValueAsString(genreDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
