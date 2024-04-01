package eu.senla.naumovich.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.naumovich.dto.AddressDto;
import eu.senla.naumovich.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;
    private final ObjectMapper objectMapper;

    public List<String> getAll() throws JsonProcessingException {
        List<AddressDto> addressesDto = addressService.getAll();
        List<String> addressesJSON = new ArrayList<>();
        for(AddressDto addressDto : addressesDto){
            addressesJSON.add(fromDtoToJSON(addressDto));
        }
        return addressesJSON;
    }

    public String getById(String addressJSON) throws JsonProcessingException {
        return fromDtoToJSON(addressService.getById(fromJSONToDto(addressJSON)));
    }

    public String update(String addressJSON) throws JsonProcessingException {
        return fromDtoToJSON(addressService.update(fromJSONToDto(addressJSON)));
    }

    public String create(String addressJSON) throws JsonProcessingException {
        return fromDtoToJSON(addressService.create(fromJSONToDto(addressJSON)));
    }

    public void delete(String addressJSON) throws JsonProcessingException {
        addressService.delete(fromJSONToDto(addressJSON));
    }

    private AddressDto fromJSONToDto(String addressJSON) throws JsonProcessingException {
        return objectMapper.readValue(addressJSON, AddressDto.class);
    }

    private String fromDtoToJSON(AddressDto addressDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(addressDto);
    }
}
