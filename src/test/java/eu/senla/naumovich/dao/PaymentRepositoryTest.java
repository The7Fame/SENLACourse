package eu.senla.naumovich.dao;

import eu.senla.naumovich.dao.common.BaseRepositoryTest;
import eu.senla.naumovich.entities.Payment;
import eu.senla.naumovich.repositories.PaymentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import java.util.Optional;

public class PaymentRepositoryTest extends BaseRepositoryTest {
    @Autowired
    PaymentRepository repository;

    @Test
    public void getPaymentsByUserId(){
        Page<Payment> paymentPage = repository.getPaymentsByUserId(1, applyPage());
        Assertions.assertEquals(paymentPage.getTotalElements(), 1);
    };

    @Test
    public void getByUserAndPaymentById(){
        Optional<Payment> payment = repository.getByUserAndPaymentById(1,1);
        Assertions.assertTrue(payment.isPresent());
    };

    @Test
    public void findByOrderId(){
        Optional<Payment> payment = repository.findByOrderId(1L);
        Assertions.assertTrue(payment.isPresent());
    };
}
