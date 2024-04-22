package eu.senla.naumovich.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.naumovich.dto.AddressDto;
import eu.senla.naumovich.services.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;
    private final ObjectMapper objectMapper;

    public List<String> getAll() {
        List<AddressDto> addressesDto = addressService.getAll();
        List<String> addressesJSON = addressesDto.stream().map(this::fromDtoToJSON).collect(Collectors.toList());
        return addressesJSON;
    }

    public String getById(String addressJSON) {
        return fromDtoToJSON(addressService.getById(fromJSONToDto(addressJSON)));
    }

    public String update(String addressJSON) {
        return fromDtoToJSON(addressService.update(fromJSONToDto(addressJSON)));
    }

    public void create(String addressJSON) {
        addressService.create(fromJSONToDto(addressJSON));
    }

    public void delete(String addressJSON) {
        addressService.delete(fromJSONToDto(addressJSON));
    }

    private AddressDto fromJSONToDto(String addressJSON) {
        try {
            return objectMapper.readValue(addressJSON, AddressDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String fromDtoToJSON(AddressDto addressDto) {
        try {
            return objectMapper.writeValueAsString(addressDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
