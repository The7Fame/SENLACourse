package eu.senla.naumovich.dto.privilege;

import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "Field must be filled in")
    private String privilegeName;
}
