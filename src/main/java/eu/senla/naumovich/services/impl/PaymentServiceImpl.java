package eu.senla.naumovich.services.impl;

import eu.senla.naumovich.dao.repository.PaymentRepository;
import eu.senla.naumovich.dto.PaymentDto;
import eu.senla.naumovich.entities.Payment;
import eu.senla.naumovich.exceptions.NoRecords;
import eu.senla.naumovich.mapper.PaymentMapper;
import eu.senla.naumovich.services.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public List<PaymentDto> getAll() {
        List<Payment> payments = paymentRepository.getAll();
        if (payments.isEmpty()) {
            return Collections.emptyList();
        }
        return payments.stream()
                .map(paymentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentDto getById(Long id) {

        return paymentMapper
                .toDto(paymentRepository.findById(id).orElseThrow(() -> new NoRecords("No record with such ID " + id)));

    }

    @Override
    public PaymentDto update(PaymentDto payment) {

        return paymentMapper.toDto(paymentRepository.update(paymentMapper.toEntity(payment)));

    }

    @Override
    public PaymentDto create(PaymentDto payment) {
        try {
            return paymentMapper.toDto(paymentRepository.create(paymentMapper.toEntity(payment)));
        } catch (Exception e) {
            throw new NoRecords("Cannot create record");
        }
    }

    @Override
    public void delete(Long id) {
        paymentRepository.deleteById(id);

    }
}
