package eu.senla.naumovich.services.service.auth;

import eu.senla.naumovich.dto.auth.AuthDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

public interface AuthService {
    public Map<Object, Object> login(AuthDto authDto);
    public void logoutUser(HttpServletRequest httpServletRequest);
}
