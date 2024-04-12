package eu.senla.naumovich.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.naumovich.dto.PrivilegeDto;
import eu.senla.naumovich.services.service.PrivilegeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PrivilegeController {
    private final PrivilegeService privilegeService;
    private final ObjectMapper objectMapper;

    public List<String> getAll() {
        List<PrivilegeDto> privilegesDto = privilegeService.getAll();
        List<String> privilegesJSON = privilegesDto.stream().map(this::fromDtoToJSON).collect(Collectors.toList());
        return privilegesJSON;
    }

    public String getById(String addressJSON) {
        return fromDtoToJSON(privilegeService.getById(fromJSONToDto(addressJSON)));
    }

    public String update(String privilegeJSON) {
        return fromDtoToJSON(privilegeService.update(fromJSONToDto(privilegeJSON)));
    }

    public String create(String privilegeJSON) {
        return fromDtoToJSON(privilegeService.create(fromJSONToDto(privilegeJSON)));
    }

    public void delete(String privilegeJSON) {
        privilegeService.delete(fromJSONToDto(privilegeJSON));
    }

    private PrivilegeDto fromJSONToDto(String privilegeJSON) {
        try {
            return objectMapper.readValue(privilegeJSON, PrivilegeDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String fromDtoToJSON(PrivilegeDto privilegeDto) {
        try {
            return objectMapper.writeValueAsString(privilegeDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
