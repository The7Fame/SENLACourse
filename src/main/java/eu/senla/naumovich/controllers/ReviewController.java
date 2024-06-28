package eu.senla.naumovich.controllers;

import eu.senla.naumovich.controllers.common.CRUDInterface;
import eu.senla.naumovich.dto.review.ReviewCreateDto;
import eu.senla.naumovich.dto.review.ReviewDto;
import eu.senla.naumovich.dto.review.ReviewShortDto;
import eu.senla.naumovich.security.SecurityUser;
import eu.senla.naumovich.services.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController implements CRUDInterface<ReviewDto, ReviewShortDto> {
    private final ReviewService reviewService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<ReviewShortDto>> getAll(@RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sort", defaultValue = "id") String sort) {
        log.info("An attempt to get reviews");
        List<ReviewShortDto> reviewDto = reviewService.getAll(page, size, sort);
        return ResponseEntity.ok(reviewDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ReviewDto> getById(@PathVariable("id") Long id) {
        log.info("An attempt to get publisher by id {}", id);
        ReviewDto reviewDto = reviewService.getById(id);
        return ResponseEntity.ok(reviewDto);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> update(@Valid @RequestBody ReviewDto reviewDto) {
        log.info("An attempt to update review");
        ReviewDto review = reviewService.update(reviewDto);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> create(@Valid @RequestBody ReviewDto reviewDto) {
        log.info("An attempt to create publisher");
        ReviewDto review = reviewService.create(reviewDto);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        log.info("An attempt to delete publisher by id {}", id);
        reviewService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/book/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> createReview(@AuthenticationPrincipal SecurityUser securityUser,
            @PathVariable("id") Long id,
            @Valid @RequestBody ReviewCreateDto reviewCreateDto) {

        log.info("An attempt to create user review to book id {}", id);
        ReviewDto reviewDto = reviewService.createReviewByUser(securityUser, id, reviewCreateDto);
        return new ResponseEntity<>(reviewDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}/book")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> deleteUserOrder(@AuthenticationPrincipal SecurityUser securityUser,
            @PathVariable("id") Long id) {
        log.info("An attempt to delete user review by id {}", id);
        reviewService.deleteUserReviewById(securityUser, id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "The record was deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}
