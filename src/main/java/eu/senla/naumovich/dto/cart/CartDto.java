package eu.senla.naumovich.dto.cart;

import eu.senla.naumovich.dto.user.UserDto;
import eu.senla.naumovich.dto.book.BookDto;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Builder
@Jacksonized
public class CartDto {
    private Long id;
    private BookDto book;
    private UserDto user;
}
