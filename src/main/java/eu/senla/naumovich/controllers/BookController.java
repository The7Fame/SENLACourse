package eu.senla.naumovich.controllers;

import eu.senla.naumovich.controllers.common.CRUDInterface;
import eu.senla.naumovich.dto.BookDto;
import eu.senla.naumovich.dto.ReviewDto;
import eu.senla.naumovich.dto.ReviewForBookDto;
import eu.senla.naumovich.services.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController implements CRUDInterface<BookDto> {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDto>> getAll(@RequestParam(name = "page", defaultValue = "1") int page,
                                                @RequestParam(name = "size", defaultValue = "10") int size) {
        List<BookDto> bookDto = bookService.getAll(page, size);
        return ResponseEntity.ok(bookDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getById(@PathVariable("id") Long id) {
        BookDto bookDto = bookService.getById(id);
        return ResponseEntity.ok(bookDto);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('UPDATE_BOOK')")
    public ResponseEntity<?> update(@RequestBody BookDto bookDto) {
        bookService.update(bookDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_BOOK')")
    public ResponseEntity<?> create(@RequestBody BookDto bookDto) {
        bookService.create(bookDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_BOOK')")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id:\\d+}/reviews")
    public ResponseEntity<List<ReviewForBookDto>> getBookReviews(@PathVariable("id") Long id){
        List<ReviewForBookDto> reviews = bookService.getReviewsByBookId(id);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookDto>> getBookByGenre(@RequestParam(name = "genre", defaultValue = "0") int genre,
                                                        @RequestParam(name = "title", defaultValue = "") String booTitle,
                                                        @RequestParam(name = "page", defaultValue = "1") int page,
                                                        @RequestParam(name = "size", defaultValue = "10") int size){
        System.out.println(genre);
        List<BookDto> books = bookService.getBooksByGenreAndTitle(genre, booTitle, page, size);
        return ResponseEntity.ok(books);
    }
}
