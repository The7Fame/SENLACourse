package eu.senla.naumovich.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.naumovich.dto.CartDto;
import eu.senla.naumovich.services.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final ObjectMapper objectMapper;

    public List<String> getAll() {
        List<CartDto> cartsDto = cartService.getAll();
        List<String> cartsJSON = cartsDto.stream().map(this::fromDtoToJSON).collect(Collectors.toList());
        return cartsJSON;
    }

    public String getById(String cartJSON) {
        return fromDtoToJSON(cartService.getById(fromJSONToDto(cartJSON)));
    }

    public String update(String cartJSON) {
        return fromDtoToJSON(cartService.update(fromJSONToDto(cartJSON)));
    }

    public String create(String cartJSON) {
        return fromDtoToJSON(cartService.create(fromJSONToDto(cartJSON)));
    }

    public void delete(String cartJSON) {
        cartService.delete(fromJSONToDto(cartJSON));
    }
    private CartDto fromJSONToDto(String cartJSON) {
        try {
            return objectMapper.readValue(cartJSON, CartDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String fromDtoToJSON(CartDto cartDto) {
        try {
            return objectMapper.writeValueAsString(cartDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
