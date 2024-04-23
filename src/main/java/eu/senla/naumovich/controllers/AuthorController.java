package eu.senla.naumovich.controllers;

import eu.senla.naumovich.controllers.common.CRUDInterface;
import eu.senla.naumovich.dto.AuthorDto;
import eu.senla.naumovich.services.service.AuthorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController implements CRUDInterface<AuthorDto> {
    @Autowired
    private AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<AuthorDto>> getAll() {
        List<AuthorDto> authorDto = authorService.getAll();
        return ResponseEntity.ok(authorDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> getById(@PathVariable("id") Long id) {
        AuthorDto authorDto = authorService.getById(id);
        if (authorDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(authorDto);
    }

    @PutMapping
    public ResponseEntity<?> update(AuthorDto authorDto) {
        authorService.update(authorDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> create(AuthorDto authorDto) {
        authorService.create(authorDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
