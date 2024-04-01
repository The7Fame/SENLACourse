package eu.senla.naumovich.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.naumovich.dto.AuthorDto;
import eu.senla.naumovich.services.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;
    private final ObjectMapper objectMapper;

    public List<String> getAll() throws JsonProcessingException {
        List<AuthorDto> authorsDto = authorService.getAll();
        List<String> authorsJSON = new ArrayList<>();
        for(AuthorDto authorDto : authorsDto){
            authorsJSON.add(fromDtoToJSON(authorDto));
        }
        return authorsJSON;
    }

    public String getById(String authorJSON) throws JsonProcessingException {
        return fromDtoToJSON(authorService.getById(fromJSONToDto(authorJSON)));
    }

    public String update(String authorJSON) throws JsonProcessingException {
        return fromDtoToJSON(authorService.update(fromJSONToDto(authorJSON)));
    }

    public String create(String authorJSON) throws JsonProcessingException {
        return fromDtoToJSON(authorService.create(fromJSONToDto(authorJSON)));
    }

    public void delete(String authorJSON) throws JsonProcessingException {
        authorService.delete(fromJSONToDto(authorJSON));
    }

    private AuthorDto fromJSONToDto(String authorJSON) throws JsonProcessingException {
        return objectMapper.readValue(authorJSON, AuthorDto.class);
    }

    private String fromDtoToJSON(AuthorDto authorDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(authorDto);
    }
}
