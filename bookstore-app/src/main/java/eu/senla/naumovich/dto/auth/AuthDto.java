package eu.senla.naumovich.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Builder
@Jacksonized
public class AuthDto {
    @NotBlank(message = "Field must be filled in")
    private String email;
    @NotBlank(message = "Field must be filled in")
    private String password;
}
