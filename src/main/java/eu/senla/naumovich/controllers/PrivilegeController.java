package eu.senla.naumovich.controllers;

import eu.senla.naumovich.controllers.common.CRUDInterface;
import eu.senla.naumovich.dto.privilege.PrivilegeDto;
import eu.senla.naumovich.services.service.PrivilegeService;
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
@RequestMapping("/privilege")
public class PrivilegeController implements CRUDInterface<PrivilegeDto, PrivilegeDto> {

    private final PrivilegeService privilegeService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<PrivilegeDto>> getAll(@RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sort", defaultValue = "id") String sort) {
        log.info("An attempt to get privileges");
        List<PrivilegeDto> privilegeDto = privilegeService.getAll(page, size, sort);
        return ResponseEntity.ok(privilegeDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PrivilegeDto> getById(@PathVariable("id") Long id) {
        log.info("An attempt to get privilege by id {}", id);
        PrivilegeDto privilegeDto = privilegeService.getById(id);
        return ResponseEntity.ok(privilegeDto);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> update(@Valid @RequestBody PrivilegeDto privilegeDto) {
        log.info("An attempt to update privilege");
        PrivilegeDto privilege = privilegeService.update(privilegeDto);
        return new ResponseEntity<>(privilege, HttpStatus.CREATED);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> create(@Valid @RequestBody PrivilegeDto privilegeDto) {
        log.info("An attempt to create privilege");
        PrivilegeDto privilege = privilegeService.create(privilegeDto);
        return new ResponseEntity<>(privilege, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        log.info("An attempt to delete privilege by id {}", id);
        privilegeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
