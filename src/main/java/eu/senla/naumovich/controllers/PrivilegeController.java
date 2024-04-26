package eu.senla.naumovich.controllers;

import eu.senla.naumovich.controllers.common.CRUDInterface;
import eu.senla.naumovich.dto.PrivilegeDto;
import eu.senla.naumovich.services.service.PrivilegeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/privilege")
public class PrivilegeController implements CRUDInterface<PrivilegeDto> {

    private final PrivilegeService privilegeService;

    @GetMapping
    public ResponseEntity<List<PrivilegeDto>> getAll() {
        List<PrivilegeDto> privilegeDto = privilegeService.getAll();
        return ResponseEntity.ok(privilegeDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrivilegeDto> getById(@PathVariable("id") Long id) {
        PrivilegeDto privilegeDto = privilegeService.getById(id);
        return ResponseEntity.ok(privilegeDto);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody PrivilegeDto privilegeDto) {
        privilegeService.update(privilegeDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PrivilegeDto privilegeDto) {
        privilegeService.create(privilegeDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        privilegeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
