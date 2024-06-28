package eu.senla.naumovich.service;

import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.dto.payment.PaymentDto;
import eu.senla.naumovich.dto.payment.PaymentShortDto;
import eu.senla.naumovich.entities.Payment;
import eu.senla.naumovich.entities.User;
import eu.senla.naumovich.mapper.PaymentMapper;
import eu.senla.naumovich.repositories.PaymentRepository;
import eu.senla.naumovich.security.SecurityUser;
import eu.senla.naumovich.services.impl.PaymentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
        String sort = "id";
        Pageable pageable = PageRequest.of(0, 5, Sort.by(sort));
        Payment payment = new Payment();
        Page<Payment> paymentPage = new PageImpl<>(Collections.singletonList(payment));
        when(repository.findAll(pageable)).thenReturn(paymentPage);
        when(mapper.toDtoList(anyList())).thenReturn(Collections.singletonList(PaymentShortDto.builder().build()));
        List<PaymentShortDto> result = service.getAll(1, 5, sort);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(repository).findAll(pageable);
        verify(mapper).toDtoList(anyList());
    }

    @Test
    public void getByIdTest() {
        Payment payment = Generator.createPayment();
        PaymentDto paymentDto = Generator.createPaymentDto();
        when(repository.findById(1L)).thenReturn(Optional.ofNullable(payment));
        when(mapper.toDto(payment)).thenReturn(paymentDto);
        PaymentDto result = service.getById(1L);
        Assertions.assertEquals(paymentDto, result);
        verify(repository).findById(1L);
        verify(mapper).toDto(payment);
    }

    @Test
    public void updateTest() {
        Payment payment = Generator.createPayment();
        PaymentDto paymentDto = Generator.createPaymentDto();
        when(mapper.toEntity(paymentDto)).thenReturn(payment);
        when(repository.save(payment)).thenReturn(payment);
        when(mapper.toDto(payment)).thenReturn(paymentDto);
        PaymentDto result = service.update(paymentDto);
        Assertions.assertEquals(paymentDto, result);
        verify(repository).save(payment);
        verify(mapper).toEntity(paymentDto);
        verify(mapper).toDto(payment);
    }

    @Test
    public void createTest() {
        Payment payment = Generator.createPayment();
        PaymentDto paymentDto = Generator.createPaymentDto();
        when(mapper.toEntity(paymentDto)).thenReturn(payment);
        when(repository.save(payment)).thenReturn(payment);
        when(mapper.toDto(payment)).thenReturn(paymentDto);
        PaymentDto result = service.create(paymentDto);
        Assertions.assertEquals(paymentDto, result);
        verify(repository).save(payment);
        verify(mapper).toEntity(paymentDto);
        verify(mapper).toDto(payment);
    }

    @Test
    public void deleteTest() {
        Payment payment = Generator.createPayment();
        doNothing().when(repository).deleteById(payment.getId());
        service.delete(payment.getId());
        verify(repository).deleteById(payment.getId());
    }

    @Test
    public void getUserPaymentsTest(){
        Pageable pageable = PageRequest.of(0, 5);
        User user = Generator.createUser();
        SecurityUser securityUser = (SecurityUser) SecurityUser.fromUser(user);
        Page<Payment> paymentPage = new PageImpl<>(Collections.emptyList());
        when(repository.getPaymentsByUserId(securityUser.getId(), PageRequest.of(0, 5))).thenReturn(paymentPage);
        when(mapper.toDtoList(paymentPage.getContent())).thenReturn(Collections.emptyList());
        List<PaymentShortDto> result = service.getUserPayments(securityUser, 1, 5);

        assertNotNull(result);
        verify(repository).getPaymentsByUserId(securityUser.getId(), pageable);
        verify(mapper).toDtoList(paymentPage.getContent());
    }
}
