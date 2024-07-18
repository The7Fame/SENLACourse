package eu.senla.naumovich.dto.role;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Builder
@Jacksonized
public class RoleShortDto {
    private Long id;
    private String roleName;
}
