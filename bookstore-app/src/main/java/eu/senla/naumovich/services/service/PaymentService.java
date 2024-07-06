package eu.senla.naumovich.services.service;

import eu.senla.naumovich.dto.order.OrderCreateDto;
import eu.senla.naumovich.dto.payment.PaymentDto;
import eu.senla.naumovich.dto.payment.PaymentShortDto;
import eu.senla.naumovich.security.SecurityUser;
import eu.senla.naumovich.services.service.common.AbstractService;

import java.util.List;

public interface PaymentService extends AbstractService<PaymentDto, PaymentShortDto> {
    public PaymentDto createPayment(SecurityUser securityUser, OrderCreateDto orderCreateDto);

    public List<PaymentShortDto> getUserPayments(SecurityUser securityUser, int page, int size);

    public PaymentDto getUserPaymentById(SecurityUser securityUser, Long paymentId);

}
