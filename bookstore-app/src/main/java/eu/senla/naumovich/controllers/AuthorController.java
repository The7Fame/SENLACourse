package eu.senla.naumovich.controllers;

import eu.senla.naumovich.controllers.common.CRUDInterface;
import eu.senla.naumovich.dto.author.AuthorDto;
import eu.senla.naumovich.dto.author.AuthorShortDto;
import eu.senla.naumovich.dto.book.BookShortDto;
import eu.senla.naumovich.services.service.AuthorService;
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
@RequestMapping("/author")
public class AuthorController implements CRUDInterface<AuthorDto, AuthorShortDto> {

    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<AuthorShortDto>> getAll(@RequestParam(name = "page", defaultValue = "1") int page,
                                                       @RequestParam(name = "size", defaultValue = "10") int size,
                                                       @RequestParam(name = "sort", defaultValue = "id") String sort) {
        log.info("An attempt to get authors");
        List<AuthorShortDto> authorDto = authorService.getAll(page, size, sort);
        return ResponseEntity.ok(authorDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> getById(@PathVariable("id") Long id) {
        log.info("An attempt to get address by id {}", id);
        AuthorDto authorDto = authorService.getById(id);
        return ResponseEntity.ok(authorDto);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('UPDATE_AUTHOR')")
    public ResponseEntity<?> update(@Valid @RequestBody AuthorDto authorDto) {
        log.info("An attempt to update address");
        AuthorDto author = authorService.update(authorDto);
        return new ResponseEntity<>(author, HttpStatus.CREATED);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_AUTHOR')")
    public ResponseEntity<?> create(@Valid @RequestBody AuthorDto authorDto) {
        log.info("An attempt to create address");
        AuthorDto author = authorService.create(authorDto);
        return new ResponseEntity<>(author, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_AUTHOR')")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        log.info("An attempt to delete address by id {}", id);
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookShortDto>> getAuthorBooks(@RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "name", defaultValue = "") String authorName) {
        List<BookShortDto> books = authorService.getAuthorBooks(page, size, authorName);
        return ResponseEntity.ok(books);
    }
}
