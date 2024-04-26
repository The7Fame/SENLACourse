package eu.senla.naumovich.controllers;

import eu.senla.naumovich.controllers.common.CRUDInterface;
import eu.senla.naumovich.dto.ReviewDto;
import eu.senla.naumovich.services.service.ReviewService;
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
@RequestMapping("/review")
public class ReviewController implements CRUDInterface<ReviewDto> {

    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<ReviewDto>> getAll() {
        List<ReviewDto> reviewDto = reviewService.getAll();
        return ResponseEntity.ok(reviewDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDto> getById(@PathVariable("id") Long id) {
        ReviewDto reviewDto = reviewService.getById(id);
        return ResponseEntity.ok(reviewDto);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody ReviewDto reviewDto) {
        reviewService.update(reviewDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ReviewDto reviewDto) {
        reviewService.create(reviewDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        reviewService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
