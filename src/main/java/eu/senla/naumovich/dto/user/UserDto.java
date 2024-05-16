package eu.senla.naumovich.dto.user;

import eu.senla.naumovich.dto.role.RoleDto;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@Jacksonized
public class UserDto {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private BigDecimal balance;
    private String password;
    private RoleDto role;
}
