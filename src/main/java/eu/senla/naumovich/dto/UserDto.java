package eu.senla.naumovich.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Builder
@Jacksonized
public class UserDto {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private RoleDto role;
}
