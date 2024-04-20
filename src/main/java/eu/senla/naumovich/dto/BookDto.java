package eu.senla.naumovich.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookDto {
    Long id;
    String title;
    Double price;
    String isbn;
    String genre;
    PublisherDto publisher;
}
