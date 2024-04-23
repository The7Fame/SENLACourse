package eu.senla.naumovich.dto;

import java.util.List;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    Long id;
    String roleName;
    List<PrivilegeDto> privileges;
}
