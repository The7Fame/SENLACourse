package eu.senla.naumovich.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.naumovich.dto.UserDto;
import eu.senla.naumovich.services.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ObjectMapper objectMapper;

    public List<String> getAll() {
        List<UserDto> usersDto = userService.getAll();
        List<String> usersJSON = usersDto.stream().map(this::fromDtoToJSON).collect(Collectors.toList());
        return usersJSON;
    }

    public String getById(String addressJSON) {
        return fromDtoToJSON(userService.getById(fromJSONToDto(addressJSON)));
    }

    public String update(String addressJSON) {
        return fromDtoToJSON(userService.update(fromJSONToDto(addressJSON)));
    }

    public String create(String addressJSON) {
        return fromDtoToJSON(userService.create(fromJSONToDto(addressJSON)));
    }

    public void delete(String addressJSON) {
        userService.delete(fromJSONToDto(addressJSON));
    }

    private UserDto fromJSONToDto(String userJSON) {
        try {
            return objectMapper.readValue(userJSON, UserDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String fromDtoToJSON(UserDto userDto) {
        try {
            return objectMapper.writeValueAsString(userDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
