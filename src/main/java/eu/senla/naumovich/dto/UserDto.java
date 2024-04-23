package eu.senla.naumovich.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    Long id;
    String name;
    String surname;
    String email;
    RoleDto role;
}
