package eu.senla.naumovich.dto.user;

import eu.senla.naumovich.dto.role.RoleDto;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank(message = "Field must be filled in")
    private String name;
    @NotBlank(message = "Field must be filled in")
    private String surname;
    @NotBlank(message = "Field must be filled in")
    private String email;
    @NotNull(message = "Balance cannot be null")
    @DecimalMin(value = "0.0", message = "Balance must be greater than 0")
    private BigDecimal balance;
    @NotBlank(message = "Field must be filled in")
    private String password;
    @NotNull(message = "Role information must be provided")
    private RoleDto role;
}
