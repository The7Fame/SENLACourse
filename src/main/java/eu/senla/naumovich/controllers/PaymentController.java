package eu.senla.naumovich.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.naumovich.dto.PaymentDto;
import eu.senla.naumovich.services.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    private final ObjectMapper objectMapper;

    public List<String> getAll(){
        List<PaymentDto> paymentsDto = paymentService.getAll();
        List<String> paymentsJSON = paymentsDto.stream().map(this::fromDtoToJSON).collect(Collectors.toList());
        return paymentsJSON;
    }

    public String getById(String addressJSON){
        return fromDtoToJSON(paymentService.getById(fromJSONToDto(addressJSON)));
    }

    public String update(String paymentJSON){
        return fromDtoToJSON(paymentService.update(fromJSONToDto(paymentJSON)));
    }

    public String create(String paymentJSON){
        return fromDtoToJSON(paymentService.create(fromJSONToDto(paymentJSON)));
    }

    public void delete(String paymentJSON){
        paymentService.delete(fromJSONToDto(paymentJSON));
    }
    private PaymentDto fromJSONToDto(String paymentJSON) {
        try {
            return objectMapper.readValue(paymentJSON, PaymentDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String fromDtoToJSON(PaymentDto paymentDto) {
        try {
            return objectMapper.writeValueAsString(paymentDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
