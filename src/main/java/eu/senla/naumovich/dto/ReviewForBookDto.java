package eu.senla.naumovich.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Builder
@Jacksonized
public class ReviewForBookDto {
    private Long id;
    private Long rating;
    private String text;
}
