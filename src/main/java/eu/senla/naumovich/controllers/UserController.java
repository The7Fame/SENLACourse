package eu.senla.naumovich.controllers;

import eu.senla.naumovich.controllers.common.CRUDInterface;
import eu.senla.naumovich.dto.user.UserDto;
import eu.senla.naumovich.dto.user.UserReplenishBalanceDto;
import eu.senla.naumovich.dto.user.UserShortDto;
import eu.senla.naumovich.dto.user.UserUpdateDto;
import eu.senla.naumovich.security.SecurityUser;
import eu.senla.naumovich.services.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController implements CRUDInterface<UserDto, UserShortDto> {
    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UserShortDto>> getAll(@RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sort", defaultValue = "email") String sort) {
        log.info("An attempt to get all users");
        List<UserShortDto> userDto = userService.getAll(page, size, sort);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserDto> getById(@PathVariable("id") Long id) {
        log.info("An attempt to get user by id {}", id);
        UserDto userDto = userService.getById(id);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> create(@Valid @RequestBody UserDto userDto) {
        log.info("An attempt to create user");
        UserDto user = userService.create(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('UPDATE_USER')")
    public ResponseEntity<?> update(@Valid @RequestBody UserDto userDto) {
        log.info("An attempt to update user");
        UserDto user = userService.update(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('DELETE_USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        log.info("An attempt to delete user by id {}", id);
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('GET_PROFILE')")
    @GetMapping("/my")
    public ResponseEntity<UserDto> getUserProfile(@AuthenticationPrincipal SecurityUser securityUser) {
        log.info("An attempt to get profile");
        UserDto userDto = userService.getAuthenticate(securityUser);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/my/balance")
    public ResponseEntity<UserDto> replenishBalance(@AuthenticationPrincipal SecurityUser securityUser,
            @Valid @RequestBody UserReplenishBalanceDto replenishBalanceDto) {
        log.info("An attempt to replenish balance by user");
        UserDto userDto = userService.replenishUserBalance(securityUser, replenishBalanceDto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @PutMapping("/my")
    public ResponseEntity<UserDto> updateUser(@AuthenticationPrincipal SecurityUser securityUser,
            @Valid @RequestBody UserUpdateDto userUpdateDto) {
        UserDto userDto = userService.updateUser(securityUser, userUpdateDto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }
}
