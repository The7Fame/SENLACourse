package eu.senla.naumovich.dto.role;

import eu.senla.naumovich.dto.privilege.PrivilegeDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Getter
@Setter
@Builder
@Jacksonized
public class RoleCreateDto {
    @NotBlank(message = "Field must be filled in")
    private String roleName;
    @NotBlank(message = "Field must be filled in")
    private List<PrivilegeDto> privileges;
}
