package eu.senla.naumovich.dao;

import eu.senla.naumovich.config.DaoConfig;
import eu.senla.naumovich.config.TestConfig;
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
@ContextConfiguration(classes = { TestConfig.class, DaoConfig.class })
public class PaymentRepositoryTest {
    @Autowired
    PaymentRepository repository;

    @Test
    public void createRecord() {
        Payment payment = Generator.createPayment();
        repository.create(payment);
        Assertions.assertTrue(repository.findById(payment.getId()).isPresent());
        Assertions.assertEquals(payment, repository.findById(payment.getId()).get());
    }

    @Test
    public void updateRecord() {
        Payment payment = Generator.updatePayment();
        repository.update(payment);
        Assertions.assertTrue(repository.findById(payment.getId()).isPresent());
        Assertions.assertEquals(payment, repository.findById(payment.getId()).get());
    }

    @Test
    public void deleteTest() {
        Payment payment = Generator.createPayment();
        repository.create(payment);
        repository.deleteById(payment.getId());
        Assertions.assertEquals(repository.getAll().size(), 2);
    }
}
