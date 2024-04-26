package eu.senla.naumovich.controllers;

import eu.senla.naumovich.controllers.common.CRUDInterface;
import eu.senla.naumovich.dto.BookDto;
import eu.senla.naumovich.services.service.BookService;
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
@RequestMapping("/book")
public class BookController implements CRUDInterface<BookDto> {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDto>> getAll() {
        List<BookDto> bookDto = bookService.getAll();
        return ResponseEntity.ok(bookDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getById(@PathVariable("id") Long id) {
        BookDto bookDto = bookService.getById(id);
        return ResponseEntity.ok(bookDto);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody BookDto bookDto) {
        bookService.update(bookDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody BookDto bookDto) {
        bookService.create(bookDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
