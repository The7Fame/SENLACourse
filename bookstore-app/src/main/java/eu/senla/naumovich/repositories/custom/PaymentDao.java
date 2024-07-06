package eu.senla.naumovich.repositories.custom;

import eu.senla.naumovich.entities.Payment;

import java.util.Optional;

public interface PaymentDao {
    Optional<Payment> findByOrderId(Long orderId);
}
