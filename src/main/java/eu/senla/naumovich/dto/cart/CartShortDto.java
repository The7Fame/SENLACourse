package eu.senla.naumovich.dto.cart;

import eu.senla.naumovich.dto.book.BookShortDto;
import eu.senla.naumovich.dto.user.UserShortDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Builder
@Jacksonized
public class CartShortDto {
    private Long id;
    private BookShortDto book;
    private UserShortDto user;
}