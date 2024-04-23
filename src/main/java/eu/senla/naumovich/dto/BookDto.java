package eu.senla.naumovich.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    Long id;
    String title;
    BigDecimal price;
    String isbn;
    String genre;
    PublisherDto publisher;
}
