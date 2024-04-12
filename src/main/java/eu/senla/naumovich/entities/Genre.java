package eu.senla.naumovich.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Genre {
    Long id;
    String genreName;
    List<Book> books;
}
