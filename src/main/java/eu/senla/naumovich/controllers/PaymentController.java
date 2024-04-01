package eu.senla.naumovich.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.senla.naumovich.dto.PaymentDto;
import eu.senla.naumovich.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    private final ObjectMapper objectMapper;

    public List<String> getAll() throws JsonProcessingException {
        List<PaymentDto> paymentsDto = paymentService.getAll();
        List<String> paymentsJSON = new ArrayList<>();
        for(PaymentDto paymentDto : paymentsDto){
            paymentsJSON.add(fromDtoToJSON(paymentDto));
        }
        return paymentsJSON;
    }

    public String getById(String addressJSON) throws JsonProcessingException {
        return fromDtoToJSON(paymentService.getById(fromJSONToDto(addressJSON)));
    }

    public String update(String paymentJSON) throws JsonProcessingException {
        return fromDtoToJSON(paymentService.update(fromJSONToDto(paymentJSON)));
    }

    public String create(String paymentJSON) throws JsonProcessingException {
        return fromDtoToJSON(paymentService.create(fromJSONToDto(paymentJSON)));
    }

    public void delete(String paymentJSON) throws JsonProcessingException {
        paymentService.delete(fromJSONToDto(paymentJSON));
    }
    private PaymentDto fromJSONToDto(String paymentJSON) throws JsonProcessingException {
        return objectMapper.readValue(paymentJSON, PaymentDto.class);
    }

    private String fromDtoToJSON(PaymentDto paymentDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(paymentDto);
    }
}
