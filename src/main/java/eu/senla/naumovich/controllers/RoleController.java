package eu.senla.naumovich.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.naumovich.dto.RoleDto;
import eu.senla.naumovich.services.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;
    private final ObjectMapper objectMapper;

    public List<String> getAll(){
        List<RoleDto> rolesDto = roleService.getAll();
        List<String> rolesJSON = rolesDto.stream().map(this::fromDtoToJSON).collect(Collectors.toList());
        return rolesJSON;
    }

    public String getById(String roleJSON){
        return fromDtoToJSON(roleService.getById(fromJSONToDto(roleJSON)));
    }

    public String update(String roleJSON){
        return fromDtoToJSON(roleService.update(fromJSONToDto(roleJSON)));
    }

    public String create(String roleJSON){
        return fromDtoToJSON(roleService.create(fromJSONToDto(roleJSON)));
    }

    public void delete(String roleJSON){
        roleService.delete(fromJSONToDto(roleJSON));
    }
    private RoleDto fromJSONToDto(String roleJSON)  {
        try {
            return objectMapper.readValue(roleJSON, RoleDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String fromDtoToJSON(RoleDto roleDto)  {
        try {
            return objectMapper.writeValueAsString(roleDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
