package eu.senla.naumovich.dto;

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
