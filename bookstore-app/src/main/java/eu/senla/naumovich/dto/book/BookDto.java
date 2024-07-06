package eu.senla.naumovich.dto.book;

import java.math.BigDecimal;
import java.util.List;

import eu.senla.naumovich.dto.author.AuthorDto;
import eu.senla.naumovich.dto.promotion.PromotionShortDto;
import eu.senla.naumovich.dto.publisher.PublisherDto;
import jakarta.validation.constraints.*;
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
    @NotBlank(message = "Field must be filled in")
    private String title;
    @DecimalMin(value = "1.00", message = "book price can not be less then 1.00")
    @NotNull(message = "Field must be filled in")
    private BigDecimal price;
    @NotBlank(message = "Field must be filled in")
    private String isbn;
    @NotBlank(message = "Field must be filled in")
    private String genre;
    @NotNull(message = "Publisher information must be provided")
    private PublisherDto publisher;
    @NotEmpty(message = "Authors information must be provided")
    private List<AuthorDto> authors;
    private List<PromotionShortDto> promotions;
}
