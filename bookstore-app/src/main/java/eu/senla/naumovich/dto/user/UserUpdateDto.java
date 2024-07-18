package eu.senla.naumovich.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Builder
@Jacksonized
public class UserUpdateDto {
    @NotBlank(message = "Field must be filled in")
    private String name;
    @NotBlank(message = "Field must be filled in")
    private String surname;
}
