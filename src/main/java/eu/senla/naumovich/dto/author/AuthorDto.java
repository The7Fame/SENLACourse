package eu.senla.naumovich.dto.author;

import eu.senla.naumovich.dto.book.BookShortDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Getter
@Setter
@Builder
@Jacksonized
public class AuthorDto {
    private Long id;
    @NotBlank(message = "Field must be filled in")
    private String name;
    @NotBlank(message = "Field must be filled in")
    private String surname;
    private List<BookShortDto> books;
}
