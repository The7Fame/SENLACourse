package eu.senla.naumovich.controllers.auth;

import eu.senla.naumovich.dto.auth.AuthDto;
import eu.senla.naumovich.services.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService service;

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody AuthDto authDto){
        return ResponseEntity.ok(service.login(authDto));
    }
}
