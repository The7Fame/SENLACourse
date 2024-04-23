package eu.senla.naumovich.controllers;

import eu.senla.naumovich.controllers.common.CRUDInterface;
import eu.senla.naumovich.dto.ReviewDto;
import eu.senla.naumovich.services.service.ReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController implements CRUDInterface<ReviewDto> {
    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<ReviewDto>> getAll() {
        List<ReviewDto> reviewDto = reviewService.getAll();
        return ResponseEntity.ok(reviewDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDto> getById(@PathVariable("id") Long id) {
        ReviewDto reviewDto = reviewService.getById(id);
        if (reviewDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reviewDto);
    }

    @PutMapping
    public ResponseEntity<?> update(ReviewDto reviewDto) {
        reviewService.update(reviewDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> create(ReviewDto reviewDto) {
        reviewService.create(reviewDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        reviewService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
