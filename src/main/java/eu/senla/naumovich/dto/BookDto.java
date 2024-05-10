package eu.senla.naumovich.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Builder
@Jacksonized
public class BookDto {
    private Long id;
    private String title;
    private BigDecimal price;
    private String isbn;
    private String genre;
    private PublisherDto publisher;
    private List<AuthorDto> authors;
}
