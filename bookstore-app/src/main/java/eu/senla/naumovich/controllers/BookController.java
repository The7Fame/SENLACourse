package eu.senla.naumovich.controllers;

import eu.senla.naumovich.dto.book.BookDto;
import eu.senla.naumovich.dto.book.BookShortDto;
import eu.senla.naumovich.dto.review.ReviewForBookDto;
import eu.senla.naumovich.services.service.BookService;
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
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookShortDto>> getAll(@RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sort", defaultValue = "price") String sort) {
        log.info("An attempt to get books");
        List<BookShortDto> books = bookService.getAll(page, size, sort);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getById(@PathVariable("id") Long id) {
        log.info("An attempt to get book id {}", id);
        BookDto bookDto = bookService.getById(id);
        return ResponseEntity.ok(bookDto);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_BOOK')")
    public ResponseEntity<?> create(@Valid @RequestBody BookDto bookDto) {
        log.info("An attempt to create book");
        BookDto book = bookService.create(bookDto);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('UPDATE_BOOK')")
    public ResponseEntity<?> update(@Valid @RequestBody BookDto bookDto) {
        log.info("An attempt to update book");
        BookDto book = bookService.update(bookDto);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_BOOK')")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        log.info("An attempt to delete book by id {}", id);
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}/reviews")
    public ResponseEntity<List<ReviewForBookDto>> getBookReviews(@PathVariable("id") Long id,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        List<ReviewForBookDto> reviews = bookService.getReviewsByBookId(id, page, size);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookShortDto>> getBooksByGenreAndTitle(
            @RequestParam(name = "genre", defaultValue = "0") int genre,
            @RequestParam(name = "title", defaultValue = "") String bookTitle,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        List<BookShortDto> books = bookService.getBooksByGenreAndTitle(genre, bookTitle, page, size);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/popular")
    public ResponseEntity<List<BookShortDto>> getPopularBooks(@RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        List<BookShortDto> books = bookService.getPopularBooks(page, size);
        return ResponseEntity.ok(books);
    }
}
