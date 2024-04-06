package eu.senla.naumovich.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class User {
    Long id;
    String name;
    String surname;
    String email;
    Role role;
    List<Review> reviews;
    List<Cart> carts;
    List<Order> orders;
    List<Payment> payments;
}
