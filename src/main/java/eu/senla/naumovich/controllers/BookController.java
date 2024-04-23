package eu.senla.naumovich.controllers;

import eu.senla.naumovich.controllers.common.CRUDInterface;
import eu.senla.naumovich.dto.BookDto;
import eu.senla.naumovich.services.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController implements CRUDInterface<BookDto> {
    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDto>> getAll() {
        List<BookDto> bookDto = bookService.getAll();
        return ResponseEntity.ok(bookDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getById(@PathVariable("id") Long id) {
        BookDto bookDto = bookService.getById(id);
        if (bookDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bookDto);
    }

    @PutMapping
    public ResponseEntity<?> update(BookDto bookDto) {
        bookService.update(bookDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> create(BookDto bookDto) {
        bookService.create(bookDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
