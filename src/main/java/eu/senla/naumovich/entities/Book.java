package eu.senla.naumovich.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Book {
    Long id;
    String title;
    Double price;
    Long isbn;
    Genre genre;
    Publisher publisher;
    List<Author> authors;
    List<Review> reviews;
    List<Promotion> promotions;
    List<Cart> carts;
}
