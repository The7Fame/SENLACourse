package eu.senla.naumovich.entities;

import lombok.*;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "publishers")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(name = "publisher_name")
    String publisherName;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "publisher")
    Address address;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "publisher")
    List<Book> books;
}
