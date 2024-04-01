package eu.senla.naumovich.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Cart {
    Long id;
    User user;
    Book book;
}
