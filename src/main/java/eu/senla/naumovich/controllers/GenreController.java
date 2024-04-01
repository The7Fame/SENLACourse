package eu.senla.naumovich.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.naumovich.dto.GenreDto;
import eu.senla.naumovich.services.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GenreController {
    private final GenreService genreService;
    private final ObjectMapper objectMapper;


    public List<String> getAll() throws JsonProcessingException {
        List<GenreDto> genresDto = genreService.getAll();
        List<String> genresJSON = new ArrayList<>();
        for(GenreDto genreDto : genresDto){
            genresJSON.add(fromDtoToJSON(genreDto));
        }
        return genresJSON;
    }

    public String getById(String genreJSON) throws JsonProcessingException {
        return fromDtoToJSON(genreService.getById(fromJSONToDto(genreJSON)));
    }

    public String update(String genreJSON) throws JsonProcessingException {
        return fromDtoToJSON(genreService.update(fromJSONToDto(genreJSON)));
    }

    public String create(String genreJSON) throws JsonProcessingException {
        return fromDtoToJSON(genreService.create(fromJSONToDto(genreJSON)));
    }

    public void delete(String genreJSON) throws JsonProcessingException {
        genreService.delete(fromJSONToDto(genreJSON));
    }
    private GenreDto fromJSONToDto(String genreJSON) throws JsonProcessingException {
        return objectMapper.readValue(genreJSON, GenreDto.class);
    }

    private String fromDtoToJSON(GenreDto genreDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(genreDto);
    }
}
