package eu.senla.naumovich.entities;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "promotions")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "promotion_name")
    String promotionName;
    @Column(name = "percent")
    BigDecimal percent;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "promotions")
    List<Book> books;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Promotion promotion = (Promotion) o;
        return Objects.equals(id, promotion.id) &&
                Objects.equals(promotionName, promotion.promotionName) &&
                Objects.equals(percent, promotion.percent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, promotionName, percent);
    }

    @Override
    public String toString() {
        return getClass().getName() + ": " + id;
    }
}
