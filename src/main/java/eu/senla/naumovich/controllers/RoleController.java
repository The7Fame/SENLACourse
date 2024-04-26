package eu.senla.naumovich.controllers;

import eu.senla.naumovich.controllers.common.CRUDInterface;
import eu.senla.naumovich.dto.RoleDto;
import eu.senla.naumovich.services.service.RoleService;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@RequestMapping("/role")
public class RoleController implements CRUDInterface<RoleDto> {

    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<List<RoleDto>> getAll() {
        List<RoleDto> roleDto = roleService.getAll();
        return ResponseEntity.ok(roleDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> getById(@PathVariable("id") Long id) {
        RoleDto roleDto = roleService.getById(id);
        return ResponseEntity.ok(roleDto);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody RoleDto roleDto) {
        roleService.update(roleDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody RoleDto roleDto) {
        roleService.create(roleDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
