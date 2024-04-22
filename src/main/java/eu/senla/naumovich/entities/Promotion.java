package eu.senla.naumovich.entities;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

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
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "promotions")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(name = "promotion_name")
    String promotionName;
    @Column(name = "percent")
    BigDecimal percent;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "promotions")
    List<Book> books;
}
