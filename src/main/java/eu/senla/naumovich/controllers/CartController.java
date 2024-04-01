package eu.senla.naumovich.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.naumovich.dto.CartDto;
import eu.senla.naumovich.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final ObjectMapper objectMapper;

    public List<String> getAll() throws JsonProcessingException {
        List<CartDto> cartsDto = cartService.getAll();
        List<String> cartsJSON = new ArrayList<>();
        for(CartDto cartDto : cartsDto){
            cartsJSON.add(fromDtoToJSON(cartDto));
        }
        return cartsJSON;
    }

    public String getById(String cartJSON) throws JsonProcessingException {
        return fromDtoToJSON(cartService.getById(fromJSONToDto(cartJSON)));
    }

    public String update(String cartJSON) throws JsonProcessingException {
        return fromDtoToJSON(cartService.update(fromJSONToDto(cartJSON)));
    }

    public String create(String cartJSON) throws JsonProcessingException {
        return fromDtoToJSON(cartService.create(fromJSONToDto(cartJSON)));
    }

    public void delete(String cartJSON) throws JsonProcessingException {
        cartService.delete(fromJSONToDto(cartJSON));
    }
    private CartDto fromJSONToDto(String cartJSON) throws JsonProcessingException {
        return objectMapper.readValue(cartJSON, CartDto.class);
    }

    private String fromDtoToJSON(CartDto cartDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(cartDto);
    }
}
