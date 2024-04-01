package eu.senla.naumovich.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.naumovich.dto.UserDto;
import eu.senla.naumovich.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ObjectMapper objectMapper;

    public List<String> getAll() throws JsonProcessingException {
        List<UserDto> usersDto = userService.getAll();
        List<String> usersJSON = new ArrayList<>();
        for(UserDto userDto : usersDto){
            usersJSON.add(fromDtoToJSON(userDto));
        }
        return usersJSON;
    }

    public String getById(String addressJSON) throws JsonProcessingException {
        return fromDtoToJSON(userService.getById(fromJSONToDto(addressJSON)));
    }

    public String update(String addressJSON) throws JsonProcessingException {
        return fromDtoToJSON(userService.update(fromJSONToDto(addressJSON)));
    }

    public String create(String addressJSON) throws JsonProcessingException {
        return fromDtoToJSON(userService.create(fromJSONToDto(addressJSON)));
    }

    public void delete(String addressJSON) throws JsonProcessingException {
        userService.delete(fromJSONToDto(addressJSON));
    }
    private UserDto fromJSONToDto(String userJSON) throws JsonProcessingException {
        return objectMapper.readValue(userJSON, UserDto.class);
    }

    private String fromDtoToJSON(UserDto userDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(userDto);
    }
}
