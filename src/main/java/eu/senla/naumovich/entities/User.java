package eu.senla.naumovich.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
@NamedEntityGraph(name = "graph.User.associations", attributeNodes = {
        @NamedAttributeNode("role"),
        @NamedAttributeNode("reviews"),
        @NamedAttributeNode("carts"),
        @NamedAttributeNode("orders"),
        @NamedAttributeNode("payments")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "name")
    String name;
    @Column(name = "surname")
    String surname;
    @Column(name = "email")
    String email;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    Role role;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    List<Review> reviews;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    List<Cart> carts;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    List<Order> orders;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    List<Payment> payments;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return getClass().getName() + ": " + id;
    }
}
