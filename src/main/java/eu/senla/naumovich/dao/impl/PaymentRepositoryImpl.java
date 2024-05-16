package eu.senla.naumovich.dao.impl;

import eu.senla.naumovich.dao.repository.PaymentRepository;
import eu.senla.naumovich.dao.repository.common.AbstractDao;
import eu.senla.naumovich.dto.order.OrderCreateDto;
import eu.senla.naumovich.entities.Payment;
import eu.senla.naumovich.security.SecurityUser;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentRepositoryImpl extends AbstractDao<Long, Payment> implements PaymentRepository {
    @Override
    protected Class<Payment> getEntityClass() {
        return Payment.class;
    }
}
