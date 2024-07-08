package eu.senla.naumovich.repositories;

import eu.senla.naumovich.entities.Payment;
import eu.senla.naumovich.repositories.custom.PaymentDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>, PaymentDao {
    @Query("select p from Payment p where p.user.id = :userId order by p.totalPrice")
    Page<Payment> getPaymentsByUserId(@Param("userId") long userId, Pageable pageable);

    @Query("select p from Payment p where p.user.id = :userId and p.id = :paymentId")
    Optional<Payment> getByUserAndPaymentById(@Param("userId") long userId, @Param("paymentId") long orderId);
}
