package eu.senla.naumovich.services.impl;

import eu.senla.naumovich.dao.repository.OrderRepository;
import eu.senla.naumovich.dao.repository.PaymentRepository;
import eu.senla.naumovich.dto.order.OrderCreateDto;
import eu.senla.naumovich.dto.order.OrderDto;
import eu.senla.naumovich.dto.payment.PaymentDto;
import eu.senla.naumovich.dto.user.UserDto;
import eu.senla.naumovich.entities.Order;
import eu.senla.naumovich.entities.Payment;
import eu.senla.naumovich.exceptions.NoMoneyOnBankAccount;
import eu.senla.naumovich.exceptions.NoRecords;
import eu.senla.naumovich.mapper.OrderMapper;
import eu.senla.naumovich.mapper.PaymentMapper;
import eu.senla.naumovich.security.SecurityUser;
import eu.senla.naumovich.services.service.PaymentService;
import eu.senla.naumovich.services.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final OrderMapper orderMapper;
    private final UserService userService;

    @Override
    public List<PaymentDto> getAll(int size, int page) {
        List<Payment> payments = paymentRepository.getAll(size, page);
        return paymentMapper.toDtoList(payments);
    }

    @Override
    public PaymentDto getById(Long id) {
        return paymentMapper
                .toDto(paymentRepository.findById(id).orElseThrow(() -> new NoRecords("No record with such ID " + id)));
    }

    @Override
    public PaymentDto update(PaymentDto payment) {
        PaymentDto payment2Update = getById(payment.getId());
        payment2Update.setStatus(true);
        return paymentMapper.toDto(paymentRepository.update(paymentMapper.toEntity(payment2Update)));
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

    @Transactional
    public PaymentDto createPayment(SecurityUser securityUser, OrderCreateDto orderCreateDto) {
        OrderDto orderDto = orderMapper.toDto(orderRepository.getByUserAndOrderById(
                securityUser.getId(),
                orderCreateDto.getId())
                .orElseThrow(() -> new NoRecords("No record with such ID " + orderCreateDto.getId())));
        UserDto userDto = userService.getById(securityUser.getId());
        BigDecimal orderDtoTotalPrice = orderDto.getTotalPrice();
        BigDecimal userBalance = userDto.getBalance();
        PaymentDto paymentDto = PaymentDto.builder()
                .paymentDate(new Date())
                .order(orderDto)
                .totalPrice(orderDto.getTotalPrice())
                .user(userDto).build();
        if (userBalance.compareTo(orderDtoTotalPrice) < 0){
           throw new NoMoneyOnBankAccount("Not enough money on bank account");
        }
        userDto.setBalance(userBalance.subtract(orderDtoTotalPrice));
        userService.create(userDto);
        paymentDto.setStatus(true);
        return create(paymentDto);
    }
}
