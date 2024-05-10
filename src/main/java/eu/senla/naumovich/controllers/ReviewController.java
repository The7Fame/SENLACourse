package eu.senla.naumovich.controllers;

import eu.senla.naumovich.controllers.common.CRUDInterface;
import eu.senla.naumovich.dto.ReviewDto;
import eu.senla.naumovich.services.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController implements CRUDInterface<ReviewDto> {

    private final ReviewService reviewService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<ReviewDto>> getAll(@RequestParam(name = "page", defaultValue = "1") int page,
                                                  @RequestParam(name = "size", defaultValue = "10") int size) {
        List<ReviewDto> reviewDto = reviewService.getAll(page, size);
        return ResponseEntity.ok(reviewDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<ReviewDto> getById(@PathVariable("id") Long id) {
        ReviewDto reviewDto = reviewService.getById(id);
        return ResponseEntity.ok(reviewDto);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> update(@RequestBody ReviewDto reviewDto) {
        reviewService.update(reviewDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> create(@RequestBody ReviewDto reviewDto) {
        reviewService.create(reviewDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        reviewService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
