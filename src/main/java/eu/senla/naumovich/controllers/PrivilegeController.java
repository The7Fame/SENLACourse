package eu.senla.naumovich.controllers;

import eu.senla.naumovich.controllers.common.CRUDInterface;
import eu.senla.naumovich.dto.PrivilegeDto;
import eu.senla.naumovich.services.service.PrivilegeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/privilege")
public class PrivilegeController implements CRUDInterface<PrivilegeDto> {
    @Autowired
    private PrivilegeService privilegeService;

    @GetMapping
    public ResponseEntity<List<PrivilegeDto>> getAll() {
        List<PrivilegeDto> privilegeDto = privilegeService.getAll();
        return ResponseEntity.ok(privilegeDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrivilegeDto> getById(@PathVariable("id") Long id) {
        PrivilegeDto privilegeDto = privilegeService.getById(id);
        if (privilegeDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(privilegeDto);
    }

    @PutMapping
    public ResponseEntity<?> update(PrivilegeDto privilegeDto) {
        privilegeService.update(privilegeDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> create(PrivilegeDto privilegeDto) {
        privilegeService.create(privilegeDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        privilegeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
