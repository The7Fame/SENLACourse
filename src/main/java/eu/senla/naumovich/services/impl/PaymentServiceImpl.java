package eu.senla.naumovich.services.impl;

import eu.senla.naumovich.dao.repository.PaymentRepository;
import eu.senla.naumovich.dto.PaymentDto;
import eu.senla.naumovich.entities.Payment;
import eu.senla.naumovich.exceptions.NoRecords;
import eu.senla.naumovich.mapper.PaymentMapper;
import eu.senla.naumovich.services.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public List<PaymentDto> getAll() {
        try {
            List<Payment> payments = paymentRepository.getAll();
            List<PaymentDto> paymentsDto = payments.stream()
                    .map(paymentMapper::toDto)
                    .collect(Collectors.toList());
            return paymentsDto;
        } catch (Exception e) {
            throw new NoRecords("No records");
        }
    }

    @Override
    public PaymentDto getById(Long id) {
        try {
            return paymentMapper.toDto(paymentRepository.getById(id));
        } catch (Exception e) {
            throw new NoRecords("No record with such ID " + id);
        }
    }

    @Override
    public PaymentDto update(PaymentDto payment) {
        try {
            return paymentMapper.toDto(paymentRepository.update(paymentMapper.toEntity(payment)));
        } catch (Exception e) {
            throw new NoRecords("No record with such ID " + payment.getId());
        }
    }

    @Override
    public void create(PaymentDto payment) {
        try {
            paymentRepository.create(paymentMapper.toEntity(payment));
        } catch (Exception e) {
            throw new NoRecords("Cannot create record");
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Payment payment = paymentRepository.getById(id);
            paymentRepository.delete(payment);
        } catch (Exception e) {
            throw new NoRecords("No record with such ID " + id);
        }
    }
}
