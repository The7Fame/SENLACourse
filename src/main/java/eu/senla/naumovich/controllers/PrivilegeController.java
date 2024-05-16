package eu.senla.naumovich.controllers;

import eu.senla.naumovich.controllers.common.CRUDInterface;
import eu.senla.naumovich.dto.privilege.PrivilegeDto;
import eu.senla.naumovich.services.service.PrivilegeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/privilege")
public class PrivilegeController implements CRUDInterface<PrivilegeDto> {

    private final PrivilegeService privilegeService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<PrivilegeDto>> getAll(@RequestParam(name = "page", defaultValue = "1") int page,
                                                     @RequestParam(name = "size", defaultValue = "10") int size) {
        List<PrivilegeDto> privilegeDto = privilegeService.getAll(page, size);
        return ResponseEntity.ok(privilegeDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PrivilegeDto> getById(@PathVariable("id") Long id) {
        PrivilegeDto privilegeDto = privilegeService.getById(id);
        return ResponseEntity.ok(privilegeDto);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> update(@RequestBody PrivilegeDto privilegeDto) {
        privilegeService.update(privilegeDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> create(@RequestBody PrivilegeDto privilegeDto) {
        privilegeService.create(privilegeDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        privilegeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
