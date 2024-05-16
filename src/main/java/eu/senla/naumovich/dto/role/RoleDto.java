package eu.senla.naumovich.dto.role;

import java.util.List;

import eu.senla.naumovich.dto.privilege.PrivilegeDto;
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
    private String roleName;
    private List<PrivilegeDto> privileges;
}
