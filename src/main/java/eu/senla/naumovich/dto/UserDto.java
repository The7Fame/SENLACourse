package eu.senla.naumovich.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    Long id;
    String name;
    String surname;
    String email;
    RoleDto role;
}
