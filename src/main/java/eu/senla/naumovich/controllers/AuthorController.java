package eu.senla.naumovich.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.naumovich.dto.AuthorDto;
import eu.senla.naumovich.services.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;
    private final ObjectMapper objectMapper;

    public List<String> getAll() {
        List<AuthorDto> authorsDto = authorService.getAll();
        List<String> authorsJSON = authorsDto.stream().map(this::fromDtoToJSON).collect(Collectors.toList());
        return authorsJSON;
    }

    public String getById(String authorJSON) {
        return fromDtoToJSON(authorService.getById(fromJSONToDto(authorJSON)));
    }

    public String update(String authorJSON) {
        return fromDtoToJSON(authorService.update(fromJSONToDto(authorJSON)));
    }

    public void create(String authorJSON) {
        authorService.create(fromJSONToDto(authorJSON));
    }

    public void delete(String authorJSON) {
        authorService.delete(fromJSONToDto(authorJSON));
    }

    private AuthorDto fromJSONToDto(String authorJSON) {
        try {
            return objectMapper.readValue(authorJSON, AuthorDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String fromDtoToJSON(AuthorDto authorDto) {
        try {
            return objectMapper.writeValueAsString(authorDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
