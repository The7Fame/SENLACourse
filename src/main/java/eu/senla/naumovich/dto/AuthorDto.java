package eu.senla.naumovich.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDto {
    Long id;
    String name;
    String surname;
}
