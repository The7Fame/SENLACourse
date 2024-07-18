package eu.senla.naumovich.services.impl;

import eu.senla.naumovich.service.MailService;
import eu.senla.naumovich.dto.order.OrderCreateDto;
import eu.senla.naumovich.dto.order.OrderDto;
import eu.senla.naumovich.dto.payment.PaymentDto;
import eu.senla.naumovich.dto.payment.PaymentShortDto;
import eu.senla.naumovich.dto.user.UserDto;
import eu.senla.naumovich.entities.Payment;
import eu.senla.naumovich.exceptions.NoMoneyOnBankAccountException;
import eu.senla.naumovich.exceptions.NoRecordException;
import eu.senla.naumovich.exceptions.OrderAlreadyPaidException;
import eu.senla.naumovich.exceptions.RecordExistsException;
import eu.senla.naumovich.mapper.OrderMapper;
import eu.senla.naumovich.mapper.PaymentMapper;
import eu.senla.naumovich.repositories.OrderRepository;
import eu.senla.naumovich.repositories.PaymentRepository;
import eu.senla.naumovich.security.SecurityUser;
import eu.senla.naumovich.services.service.PaymentService;
import eu.senla.naumovich.services.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final OrderMapper orderMapper;
    private final UserService userService;
    private final MailService mailService;

    @Override
    public List<PaymentShortDto> getAll(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sort));
        Page<Payment> paymentPage = paymentRepository.findAll(pageable);
        System.out.println(paymentPage.getContent());
        return paymentMapper.toDtoList(paymentPage.getContent());
    }

    @Override
    public PaymentDto getById(Long id) {
        return paymentMapper
                .toDto(paymentRepository.findById(id).orElseThrow(() -> new NoRecordException("No record with such ID " + id)));
    }

    @Override
    public PaymentDto update(PaymentDto payment) {
        return paymentMapper.toDto(paymentRepository.save(paymentMapper.toEntity(payment)));
    }

    @Override
    public PaymentDto create(PaymentDto payment) {
        try {
            return paymentMapper.toDto(paymentRepository.save(paymentMapper.toEntity(payment)));
        } catch (Exception e) {
            throw new RecordExistsException("Record is exists");
        }
    }

    @Override
    public void delete(Long id) {
        paymentRepository.deleteById(id);
    }

    @Transactional
    public PaymentDto createPayment(SecurityUser securityUser, OrderCreateDto orderCreateDto) {
        System.out.println("HERE");
        OrderDto orderDto = orderMapper.toDto(orderRepository.getByUserAndOrderById(
                securityUser.getId(),
                orderCreateDto.getId())
                .orElseThrow(() -> new NoRecordException("No record with such ID " + orderCreateDto.getId())));

        if (paymentRepository.findByOrderId(orderCreateDto.getId()).isPresent()) {
            Payment existingPayment = paymentRepository.findByOrderId(orderCreateDto.getId()).get();
            if (existingPayment.getStatus()) {
                throw new OrderAlreadyPaidException("Order with ID " + orderCreateDto.getId() + " is already paid.");
            }
        }
        UserDto userDto = userService.getById(securityUser.getId());
        BigDecimal orderDtoTotalPrice = orderDto.getTotalPrice();
        BigDecimal userBalance = userDto.getBalance();
        PaymentDto paymentDto = PaymentDto.builder()
                .paymentDate(new Date())
                .order(orderDto)
                .totalPrice(orderDto.getTotalPrice())
                .user(userDto).build();
        if (userBalance.compareTo(orderDtoTotalPrice) < 0){
           throw new NoMoneyOnBankAccountException("Not enough money on bank account");
        }
        mailService.sendMsg(userDto.getEmail(), "Order","Your order is paid successfully");
        userDto.setBalance(userBalance.subtract(orderDtoTotalPrice));
        userService.update(userDto);
        paymentDto.setStatus(true);
        return create(paymentDto);
    }

    @Override
    public List<PaymentShortDto> getUserPayments(SecurityUser securityUser, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Payment> paymentPage = paymentRepository.getPaymentsByUserId(securityUser.getId(), pageable);
        return paymentMapper.toDtoList(paymentPage.getContent());
    }

    @Override
    public PaymentDto getUserPaymentById(SecurityUser securityUser, Long paymentId) {
        return paymentMapper.toDto(paymentRepository.getByUserAndPaymentById(securityUser.getId(), paymentId).orElseThrow(() -> new NoRecordException("No record with such ID " + paymentId)));
    }
}
