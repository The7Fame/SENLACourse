package eu.senla.naumovich.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

public class JWTException extends AuthenticationException {
    public JWTException(String msg) {
        super(msg);
    }
}
