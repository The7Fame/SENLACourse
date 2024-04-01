package eu.senla.naumovich.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.naumovich.dto.RoleDto;
import eu.senla.naumovich.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;
    private final ObjectMapper objectMapper;

    public List<String> getAll() throws JsonProcessingException {
        List<RoleDto> rolesDto = roleService.getAll();
        List<String> rolesJSON = new ArrayList<>();
        for(RoleDto roleDto : rolesDto){
            rolesJSON.add(fromDtoToJSON(roleDto));
        }
        return rolesJSON;
    }

    public String getById(String roleJSON) throws JsonProcessingException {
        return fromDtoToJSON(roleService.getById(fromJSONToDto(roleJSON)));
    }

    public String update(String roleJSON) throws JsonProcessingException {
        return fromDtoToJSON(roleService.update(fromJSONToDto(roleJSON)));
    }

    public String create(String roleJSON) throws JsonProcessingException {
        return fromDtoToJSON(roleService.create(fromJSONToDto(roleJSON)));
    }

    public void delete(String roleJSON) throws JsonProcessingException {
        roleService.delete(fromJSONToDto(roleJSON));
    }
    private RoleDto fromJSONToDto(String roleJSON) throws JsonProcessingException {
        return objectMapper.readValue(roleJSON, RoleDto.class);
    }

    private String fromDtoToJSON(RoleDto roleDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(roleDto);
    }
}
