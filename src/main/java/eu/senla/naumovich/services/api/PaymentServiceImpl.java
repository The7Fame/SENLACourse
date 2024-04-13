package eu.senla.naumovich.services.api;

import eu.senla.naumovich.dao.repository.PaymentRepository;
import eu.senla.naumovich.dto.PaymentDto;
import eu.senla.naumovich.entities.Payment;
import eu.senla.naumovich.services.mapper.PaymentMapper;
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
        List<Payment> payments = paymentRepository.getAll();
        List<PaymentDto> paymentsDto = payments.stream()
                .map(paymentMapper::toDto)
                .collect(Collectors.toList());
        return paymentsDto;
    }

    @Override
    public PaymentDto getById(PaymentDto payment) {
        return paymentMapper.toDto(paymentRepository.getById(payment.getId()));
    }

    @Override
    public PaymentDto update(PaymentDto payment) {
        return paymentMapper.toDto(paymentRepository.update(paymentMapper.toEntity(payment)));
    }

    @Override
    public void create(PaymentDto payment) {
        paymentRepository.create(paymentMapper.toEntity(payment));
    }

    @Override
    public void delete(PaymentDto payment) {
        paymentRepository.delete(paymentMapper.toEntity(payment));
    }
}
