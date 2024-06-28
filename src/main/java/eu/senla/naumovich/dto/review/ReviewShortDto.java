package eu.senla.naumovich.dto.review;

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
public class ReviewShortDto {
    private Long id;
    private Long rating;
    private String text;
//    private UserShortDto user;
//    private BookShortDto book;
}
