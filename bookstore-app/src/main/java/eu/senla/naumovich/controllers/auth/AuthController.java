package eu.senla.naumovich.controllers.auth;

import eu.senla.naumovich.dto.auth.AuthDto;
import eu.senla.naumovich.services.service.auth.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService service;

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@Valid @RequestBody AuthDto authDto) {
        log.info("An attempt to log into the system");
        return ResponseEntity.ok(service.login(authDto));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(HttpServletRequest httpServletRequest){
        service.logoutUser(httpServletRequest);
        return ResponseEntity.ok().build();
    }
}
