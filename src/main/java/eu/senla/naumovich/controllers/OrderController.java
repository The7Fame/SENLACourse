package eu.senla.naumovich.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.naumovich.dto.OrderDto;
import eu.senla.naumovich.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final ObjectMapper objectMapper;

    public List<String> getAll() throws JsonProcessingException {
        List<OrderDto> ordersDto = orderService.getAll();
        List<String> ordersJSON = new ArrayList<>();
        for(OrderDto orderDto : ordersDto){
            ordersJSON.add(fromDtoToJSON(orderDto));
        }
        return ordersJSON;
    }

    public String getById(String orderJSON) throws JsonProcessingException {
        return fromDtoToJSON(orderService.getById(fromJSONToDto(orderJSON)));
    }

    public String update(String orderJSON) throws JsonProcessingException {
        return fromDtoToJSON(orderService.update(fromJSONToDto(orderJSON)));
    }

    public String create(String orderJSON) throws JsonProcessingException {
        return fromDtoToJSON(orderService.create(fromJSONToDto(orderJSON)));
    }

    public void delete(String orderJSON) throws JsonProcessingException {
        orderService.delete(fromJSONToDto(orderJSON));
    }

    private OrderDto fromJSONToDto(String orderJSON) throws JsonProcessingException {
        return objectMapper.readValue(orderJSON, OrderDto.class);
    }
    private String fromDtoToJSON(OrderDto orderDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(orderDto);
    }
}
