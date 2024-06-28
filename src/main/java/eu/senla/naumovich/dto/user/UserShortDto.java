package eu.senla.naumovich.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Builder
@Jacksonized
public class UserShortDto {
    private Long id;
    private String name;
    private String surname;
}
