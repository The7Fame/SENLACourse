package eu.senla.naumovich.services;

import eu.senla.naumovich.dto.PaymentDto;

import java.util.List;

public interface PaymentService {
    List<PaymentDto> getAll();

    PaymentDto getById(PaymentDto payment);

    PaymentDto update(PaymentDto payment);

    PaymentDto create(PaymentDto payment);

    void delete(PaymentDto payment);
}
