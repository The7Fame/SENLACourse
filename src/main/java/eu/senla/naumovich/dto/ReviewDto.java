package eu.senla.naumovich.dto;

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
    private Long rating;
    private String text;
    private UserDto user;
    private BookDto book;
}
