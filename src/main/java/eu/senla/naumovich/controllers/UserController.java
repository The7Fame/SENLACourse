package eu.senla.naumovich.controllers;

import eu.senla.naumovich.controllers.common.CRUDInterface;
import eu.senla.naumovich.dto.UserDto;
import eu.senla.naumovich.security.SecurityUser;
import eu.senla.naumovich.services.service.CartService;
import eu.senla.naumovich.services.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController implements CRUDInterface<UserDto> {
    private final CartService cartService;
    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UserDto>> getAll(@RequestParam(name = "page", defaultValue = "1") int page,
                                                @RequestParam(name = "size", defaultValue = "10") int size) {
        List<UserDto> userDto = userService.getAll(page, size);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserDto> getById(@PathVariable("id") Long id) {

        UserDto userDto = userService.getById(id);
        return ResponseEntity.ok(userDto);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('UPDATE_USER')")
    public ResponseEntity<?> update(@RequestBody UserDto userDto) {
        userService.update(userDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> create(@RequestBody UserDto userDto) {
        userService.create(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAuthority('DELETE_USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/my")
    public ResponseEntity<UserDto> getUserProfile(Authentication authentication) {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        UserDto userDto = userService.getById(securityUser.getId());
        return ResponseEntity.ok(userDto);
    }
}
