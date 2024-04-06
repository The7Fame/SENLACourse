package eu.senla.naumovich.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.naumovich.dto.OrderDto;
import eu.senla.naumovich.services.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final ObjectMapper objectMapper;

    public List<String> getAll() {
        List<OrderDto> ordersDto = orderService.getAll();
        List<String> ordersJSON = ordersDto.stream().map(this::fromDtoToJSON).collect(Collectors.toList());
        return ordersJSON;
    }

    public String getById(String orderJSON){
        return fromDtoToJSON(orderService.getById(fromJSONToDto(orderJSON)));
    }

    public String update(String orderJSON){
        return fromDtoToJSON(orderService.update(fromJSONToDto(orderJSON)));
    }

    public String create(String orderJSON){
        return fromDtoToJSON(orderService.create(fromJSONToDto(orderJSON)));
    }

    public void delete(String orderJSON){
        orderService.delete(fromJSONToDto(orderJSON));
    }

    private OrderDto fromJSONToDto(String orderJSON) {
        try {
            return objectMapper.readValue(orderJSON, OrderDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    private String fromDtoToJSON(OrderDto orderDto) {
        try {
            return objectMapper.writeValueAsString(orderDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
