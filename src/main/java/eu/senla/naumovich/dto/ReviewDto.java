package eu.senla.naumovich.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {
    Long id;
    Long rating;
    String text;
    UserDto user;
    BookDto book;
}
