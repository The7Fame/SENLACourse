package eu.senla.naumovich.entities;

import lombok.*;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "genres")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "genre_name")
    String genreName;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "genre")
    List<Book> books;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Genre genre = (Genre) o;
        return Objects.equals(id, genre.id) &&
                Objects.equals(genreName, genre.genreName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, genreName);
    }

    @Override
    public String toString() {
        return getClass().getName() + ": " + id;
    }
}
