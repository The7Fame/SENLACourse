package eu.senla.naumovich.dto.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Builder
@Jacksonized
public class AuthDto {
    private String email;
    private String password;
}
