package eu.senla.naumovich.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
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
}
