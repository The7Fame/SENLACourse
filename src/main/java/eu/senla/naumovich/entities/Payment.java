package eu.senla.naumovich.entities;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "status")
    Boolean status;
    @Column(name = "payment_date")
    Date paymentDate;
    @Column(name = "total_price")
    BigDecimal totalPrice;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    Order order;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id) &&
                Objects.equals(status, payment.status) &&
                Objects.equals(paymentDate, payment.paymentDate) &&
                Objects.equals(totalPrice, payment.totalPrice) &&
                Objects.equals(user, payment.user) &&
                Objects.equals(order, payment.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, paymentDate, totalPrice, user, order);
    }

    @Override
    public String toString() {
        return getClass().getName() + ": " + id;
    }
}
