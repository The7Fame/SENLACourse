package eu.senla.naumovich.services.api;

import eu.senla.naumovich.dao.repository.PaymentRepository;
import eu.senla.naumovich.dto.PaymentDto;
import eu.senla.naumovich.entities.Payment;
import eu.senla.naumovich.services.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PaymentDto> getAll() {
        List<Payment> payments = paymentRepository.getAll();
        List<PaymentDto> paymentsDto = payments.stream()
                .map(payment -> modelMapper.map(payment, PaymentDto.class))
                .collect(Collectors.toList());
        return paymentsDto;
    }

    @Override
    public PaymentDto getById(PaymentDto payment) {
        return modelMapper.map(paymentRepository.getById(modelMapper.map(payment, Payment.class)), PaymentDto.class);
    }

    @Override
    public PaymentDto update(PaymentDto payment) {
        return modelMapper.map(paymentRepository.update(modelMapper.map(payment, Payment.class)), PaymentDto.class);
    }

    @Override
    public PaymentDto create(PaymentDto payment) {
        return modelMapper.map(paymentRepository.create(modelMapper.map(payment, Payment.class)), PaymentDto.class);
    }

    @Override
    public void delete(PaymentDto payment) {
        paymentRepository.delete(modelMapper.map(payment, Payment.class));
    }
}
