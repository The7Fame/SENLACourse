package eu.senla.naumovich.services;

import eu.senla.naumovich.dao.repository.PaymentRepository;
import eu.senla.naumovich.dto.PaymentDto;
import eu.senla.naumovich.entities.Payment;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PaymentDto> getAll() {
        List<PaymentDto> paymentsDto = new ArrayList<>();
        List<Payment> payments = paymentRepository.getAll();
        for(Payment payment : payments){
            paymentsDto.add(modelMapper.map(payment, PaymentDto.class));
        }
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
