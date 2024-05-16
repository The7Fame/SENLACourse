package eu.senla.naumovich.controllers;

import eu.senla.naumovich.controllers.common.CRUDInterface;
import eu.senla.naumovich.dto.author.AuthorDto;
import eu.senla.naumovich.services.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/author")
public class AuthorController implements CRUDInterface<AuthorDto> {

    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<AuthorDto>> getAll(@RequestParam(name = "page", defaultValue = "1") int page,
                                                  @RequestParam(name = "size", defaultValue = "10") int size) {
        List<AuthorDto> authorDto = authorService.getAll(page, size);
        return ResponseEntity.ok(authorDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> getById(@PathVariable("id") Long id) {
        AuthorDto authorDto = authorService.getById(id);
        return ResponseEntity.ok(authorDto);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<?> update(@RequestBody AuthorDto authorDto) {
        authorService.update(authorDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<?> create(@RequestBody AuthorDto authorDto) {
        authorService.create(authorDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
