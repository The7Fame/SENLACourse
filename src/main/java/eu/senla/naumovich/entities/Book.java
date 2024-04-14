package eu.senla.naumovich.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
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
    Long id;
    @Column(name = "title")
    String title;
    @Column(name = "price")
    BigDecimal price;
    @Column(name = "isbn")
    String isbn;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    Genre genre;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    Publisher publisher;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "authors_books", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
    List<Author> authors;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
    List<Review> reviews;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "books_promotions", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "promotion_id"))
    List<Promotion> promotions;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
    List<Cart> carts;
}
