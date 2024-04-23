package eu.senla.naumovich.dao;

import eu.senla.naumovich.TestConfig;
import eu.senla.naumovich.dao.impl.PaymentRepositoryImpl;
import eu.senla.naumovich.dao.repository.PaymentRepository;
import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.entities.Payment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { TestConfig.class, PaymentRepositoryImpl.class })
public class PaymentRepositoryTest {
    @Autowired
    PaymentRepository repository;

    @Test
    public void createRecord() {
        Payment payment = Generator.createPayment();
        repository.create(payment);
        Assertions.assertEquals(payment, repository.getById(payment.getId()));
    }

    @Test
    public void updateRecord() {
        Payment payment = Generator.updatePayment();
        repository.update(payment);
        Assertions.assertEquals(payment, repository.getById(payment.getId()));
    }

    @Test
    public void deleteTest() {
        Payment payment = Generator.createPayment();
        repository.delete(payment);
        Assertions.assertNull(repository.getById(payment.getId()));
    }
}
