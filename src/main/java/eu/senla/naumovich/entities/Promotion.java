package eu.senla.naumovich.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Promotion {
    Long id;
    String promotionName;
    Double percent;
    List<Book> books;
}
