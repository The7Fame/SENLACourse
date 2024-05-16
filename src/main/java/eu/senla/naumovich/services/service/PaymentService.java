package eu.senla.naumovich.services.service;

import eu.senla.naumovich.dto.order.OrderCreateDto;
import eu.senla.naumovich.dto.payment.PaymentDto;
import eu.senla.naumovich.exceptions.NoMoneyOnBankAccount;
import eu.senla.naumovich.security.SecurityUser;
import eu.senla.naumovich.services.service.common.AbstractService;

public interface PaymentService extends AbstractService<PaymentDto> {
    public PaymentDto createPayment(SecurityUser securityUser, OrderCreateDto orderCreateDto);
}
