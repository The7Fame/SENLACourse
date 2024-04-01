package eu.senla.naumovich.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.naumovich.dto.PrivilegeDto;
import eu.senla.naumovich.services.PrivilegeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PrivilegeController {
    private final PrivilegeService privilegeService;
    private final ObjectMapper objectMapper;

    public List<String> getAll() throws JsonProcessingException {
        List<PrivilegeDto> privilegesDto = privilegeService.getAll();
        List<String> privilegesJSON = new ArrayList<>();
        for(PrivilegeDto privilegeDto : privilegesDto){
            privilegesJSON.add(fromDtoToJSON(privilegeDto));
        }
        return privilegesJSON;
    }

    public String getById(String addressJSON) throws JsonProcessingException {
        return fromDtoToJSON(privilegeService.getById(fromJSONToDto(addressJSON)));
    }

    public String update(String privilegeJSON) throws JsonProcessingException {
        return fromDtoToJSON(privilegeService.update(fromJSONToDto(privilegeJSON)));
    }

    public String create(String privilegeJSON) throws JsonProcessingException {
        return fromDtoToJSON(privilegeService.create(fromJSONToDto(privilegeJSON)));
    }

    public void delete(String privilegeJSON) throws JsonProcessingException {
        privilegeService.delete(fromJSONToDto(privilegeJSON));
    }
    private PrivilegeDto fromJSONToDto(String privilegeJSON) throws JsonProcessingException {
        return objectMapper.readValue(privilegeJSON, PrivilegeDto.class);
    }

    private String fromDtoToJSON(PrivilegeDto privilegeDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(privilegeDto);
    }
}
