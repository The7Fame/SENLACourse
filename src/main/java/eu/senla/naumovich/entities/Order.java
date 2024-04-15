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
import jakarta.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "order_date")
    Date orderDate;
    @Column(name = "total_price")
    BigDecimal totalPrice;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(orderDate, order.orderDate) &&
                Objects.equals(totalPrice, order.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderDate, totalPrice);
    }

    @Override
    public String toString() {
        return getClass().getName() + ": " + id + "price: " + totalPrice;
    }
}
