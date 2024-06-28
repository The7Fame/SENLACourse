package eu.senla.naumovich.dto.role;

import java.util.List;

import eu.senla.naumovich.dto.privilege.PrivilegeDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Builder
@Jacksonized
public class RoleDto {
    private Long id;
    @NotBlank(message = "Field must be filled in")
    private String roleName;
    @NotNull(message = "Privileges information must be provided")
    private List<PrivilegeDto> privileges;
}
