package eu.senla.naumovich.dto.review;

import eu.senla.naumovich.dto.book.BookDto;
import eu.senla.naumovich.dto.user.UserDto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Builder
@Jacksonized
public class ReviewDto {
    private Long id;
    @NotNull(message = "Field must be filled in")
    @Min(value = 0, message = "Rating must be at least 0")
    @Max(value = 5, message = "Rating must be no more than 5")
    private Long rating;
    @NotBlank(message = "Field must be filled in")
    private String text;
    @NotNull(message = "User information must be provided")
    private UserDto user;
    @NotNull(message = "Book information must be provided")
    private BookDto book;
}
