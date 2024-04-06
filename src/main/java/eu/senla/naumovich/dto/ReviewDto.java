package eu.senla.naumovich.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewDto {
    Long id;
    Long rating;
    String text;
    UserDto user;
    BookDto book;
}
