package eu.senla.naumovich.controllers;

import eu.senla.naumovich.controllers.common.CRUDInterface;
import eu.senla.naumovich.dto.AuthorDto;
import eu.senla.naumovich.services.service.AuthorService;
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
@RequestMapping("/author")
public class AuthorController implements CRUDInterface<AuthorDto> {

    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<AuthorDto>> getAll() {
        List<AuthorDto> authorDto = authorService.getAll();
        return ResponseEntity.ok(authorDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> getById(@PathVariable("id") Long id) {
        AuthorDto authorDto = authorService.getById(id);
        return ResponseEntity.ok(authorDto);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody AuthorDto authorDto) {
        authorService.update(authorDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AuthorDto authorDto) {
        authorService.create(authorDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
