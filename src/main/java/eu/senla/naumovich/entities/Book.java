package eu.senla.naumovich.entities;

import eu.senla.naumovich.entities.converters.GenreConverter;
import eu.senla.naumovich.entities.enums.Genre;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "books")
@NamedEntityGraph(name = "graph.Book.associations", attributeNodes = {
        @NamedAttributeNode("genre"),
        @NamedAttributeNode("publisher"),
        @NamedAttributeNode("authors"),
        @NamedAttributeNode("reviews"),
        @NamedAttributeNode("promotions"),
        @NamedAttributeNode("carts")
})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "isbn")
    private String isbn;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Convert(converter = GenreConverter.class)
    @Column(name = "genre_id")
    private Genre genre;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "authors_books", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book", cascade = CascadeType.ALL)
    private List<Review> reviews;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "books_promotions", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "promotion_id"))
    private List<Promotion> promotions;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book", cascade = CascadeType.ALL)
    private List<Cart> carts;
}
