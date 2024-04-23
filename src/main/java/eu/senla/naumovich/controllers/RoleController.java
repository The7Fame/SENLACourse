package eu.senla.naumovich.controllers;

import eu.senla.naumovich.controllers.common.CRUDInterface;
import eu.senla.naumovich.dto.RoleDto;
import eu.senla.naumovich.services.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController implements CRUDInterface<RoleDto> {
    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<List<RoleDto>> getAll() {
        List<RoleDto> roleDto = roleService.getAll();
        return ResponseEntity.ok(roleDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> getById(@PathVariable("id") Long id) {
        RoleDto roleDto = roleService.getById(id);
        if (roleDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(roleDto);
    }

    @PutMapping
    public ResponseEntity<?> update(RoleDto roleDto) {
        roleService.update(roleDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> create(RoleDto roleDto) {
        roleService.create(roleDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
