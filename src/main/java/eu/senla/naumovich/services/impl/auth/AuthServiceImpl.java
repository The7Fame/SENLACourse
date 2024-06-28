package eu.senla.naumovich.services.impl.auth;

import eu.senla.naumovich.entities.Privilege;
import eu.senla.naumovich.entities.User;
import eu.senla.naumovich.repositories.UserRepository;
import eu.senla.naumovich.security.JwtTokenProvider;
import eu.senla.naumovich.dto.auth.AuthDto;
import eu.senla.naumovich.services.service.auth.AuthService;
import eu.senla.naumovich.services.service.auth.TokenBlackListService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager manager;
    private final JwtTokenProvider provider;
    private final UserRepository repository;
    private final TokenBlackListService service;

    @Transactional
    public Map<Object, Object> login(AuthDto authDto) {
        try {
            manager.authenticate(
                    new UsernamePasswordAuthenticationToken(authDto.getEmail(), authDto.getPassword()));
            User user = repository.findByEmail(authDto.getEmail());
            String token = provider.createToken(user.getId(), user.getName(), user.getEmail(),
                    user.getRole().getRoleName(), user.getRole().getPrivileges().stream().map(Privilege::getPrivilegeName).toList());
            Map<Object, Object> resp = new HashMap<>();
            resp.put("email", authDto.getEmail());
            resp.put("token", token);
            return resp;
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password", e);
        }
    }

    @Override
    public void logoutUser(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("authorization").substring("Bearer ".length());
        service.addToken2BlackList(token);
    }
}
