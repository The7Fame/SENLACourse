package eu.senla.naumovich.service;

import eu.senla.naumovich.dao.repository.PaymentRepository;
import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.dto.PaymentDto;
import eu.senla.naumovich.entities.Payment;
import eu.senla.naumovich.mapper.PaymentMapper;
import eu.senla.naumovich.services.impl.PaymentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {
    @Mock
    private PaymentMapper mapper;
    @Mock
    PaymentRepository repository;
    @InjectMocks
    private PaymentServiceImpl service;

    @Test
    public void getAllTest() {
        Payment payment = Generator.createPayment();
        PaymentDto paymentDto = Generator.createPaymentDto();
        List<PaymentDto> paymentDtos = new ArrayList<>();
        List<Payment> payments = new ArrayList<>();
        paymentDtos.add(paymentDto);
        payments.add(payment);
        when(repository.getAll()).thenReturn(payments);
        when(mapper.toDto(payment)).thenReturn(paymentDto);
        List<PaymentDto> result = service.getAll();
        Assertions.assertEquals(paymentDtos, result);
        verify(repository).getAll();
    }

    @Test
    public void getByIdTest() {
        Payment payment = Generator.createPayment();
        PaymentDto paymentDto = Generator.createPaymentDto();
        when(repository.getById(1L)).thenReturn(payment);
        when(mapper.toDto(payment)).thenReturn(paymentDto);
        PaymentDto result = service.getById(1L);
        Assertions.assertEquals(paymentDto, result);
        verify(repository).getById(1L);
        verify(mapper).toDto(payment);
    }

    @Test
    public void updateTest() {
        Payment payment = Generator.createPayment();
        PaymentDto paymentDto = Generator.createPaymentDto();
        when(mapper.toEntity(paymentDto)).thenReturn(payment);
        when(repository.update(payment)).thenReturn(payment);
        when(mapper.toDto(payment)).thenReturn(paymentDto);
        PaymentDto result = service.update(paymentDto);
        Assertions.assertEquals(paymentDto, result);
        verify(repository).update(payment);
        verify(mapper).toEntity(paymentDto);
        verify(mapper).toDto(payment);
    }

    @Test
    public void createTest() {
        Payment payment = Generator.createPayment();
        PaymentDto paymentDto = Generator.createPaymentDto();
        when(mapper.toEntity(paymentDto)).thenReturn(payment);
        doNothing().when(repository).create(payment);
        service.create(paymentDto);
        verify(repository).create(payment);
        verify(mapper).toEntity(paymentDto);
    }

    @Test
    public void deleteTest() {
        Payment payment = Generator.createPayment();
        when(repository.getById(payment.getId())).thenReturn(payment);
        doNothing().when(repository).delete(payment);
        service.delete(payment.getId());
        verify(repository).getById(payment.getId());
        verify(repository).delete(payment);
    }
}
