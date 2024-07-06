package eu.senla.naumovich.dto.book;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Builder
@Jacksonized
public class BookToCartDto {
    @NotNull(message = "Field must be filled in")
    private Long id;
}
