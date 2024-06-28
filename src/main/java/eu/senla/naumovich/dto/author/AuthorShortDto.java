package eu.senla.naumovich.dto.author;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Builder
@Jacksonized
public class AuthorShortDto {
    private Long id;
    private String name;
    private String surname;
}
