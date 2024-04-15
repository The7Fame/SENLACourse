package eu.senla.naumovich.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "city")
    String city;
    @Column(name = "street")
    String street;
    @Column(name = "index")
    Integer index;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    Publisher publisher;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id) &&
                Objects.equals(city, address.city) &&
                Objects.equals(street, address.street) &&
                Objects.equals(index, address.index) &&
                Objects.equals(publisher, address.publisher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, city, street, index, publisher);
    }

    @Override
    public String toString() {
        return getClass().getName() + ": " + id;
    }
}
