package eu.senla.naumovich.controllers;

import eu.senla.naumovich.controllers.common.CRUDInterface;
import eu.senla.naumovich.dto.role.RoleDto;
import eu.senla.naumovich.dto.role.RoleShortDto;
import eu.senla.naumovich.services.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController implements CRUDInterface<RoleDto, RoleShortDto> {

    private final RoleService roleService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<RoleShortDto>> getAll(@RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sort", defaultValue = "id") String sort) {
        log.info("An attempt to get roles");
        List<RoleShortDto> roleDto = roleService.getAll(page, size, sort);
        return ResponseEntity.ok(roleDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<RoleDto> getById(@PathVariable("id") Long id) {
        log.info("An attempt to get role by id {}", id);
        RoleDto roleDto = roleService.getById(id);
        return ResponseEntity.ok(roleDto);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> update(@Valid @RequestBody RoleDto roleDto) {
        log.info("An attempt to update role");
        RoleDto role = roleService.update(roleDto);
        return new ResponseEntity<>(role, HttpStatus.CREATED);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> create(@Valid @RequestBody RoleDto roleDto) {
        log.info("An attempt to create role");
        RoleDto role = roleService.create(roleDto);
        return new ResponseEntity<>(role, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        log.info("An attempt to delete role by id {}", id);
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
