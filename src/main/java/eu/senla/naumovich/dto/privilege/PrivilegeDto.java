package eu.senla.naumovich.dto.privilege;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Builder
@Jacksonized
public class PrivilegeDto {
    private Long id;
    private String privilegeName;
}
