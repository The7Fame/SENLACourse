package eu.senla.naumovich.entities;

import lombok.*;

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
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(name = "name")
    String name;
    @Column(name = "surname")
    String surname;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "authors")
    List<Book> books;
}
