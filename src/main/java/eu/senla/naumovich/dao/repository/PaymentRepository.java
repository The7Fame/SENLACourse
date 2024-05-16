package eu.senla.naumovich.dao.repository;

import eu.senla.naumovich.dao.repository.common.AbstractRepository;
import eu.senla.naumovich.dto.order.OrderCreateDto;
import eu.senla.naumovich.entities.Payment;
import eu.senla.naumovich.security.SecurityUser;

public interface PaymentRepository extends AbstractRepository<Payment, Long> {
}
