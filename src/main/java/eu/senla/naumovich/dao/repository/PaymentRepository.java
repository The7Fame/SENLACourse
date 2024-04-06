package eu.senla.naumovich.dao.repository;

import eu.senla.naumovich.entities.Payment;

import java.util.List;

public interface PaymentRepository {
    List<Payment> getAll();

    Payment getById(Payment payment);

    Payment update(Payment payment);

    Payment create(Payment payment);

    void delete(Payment payment);
}
