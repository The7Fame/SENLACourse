package eu.senla.naumovich.controllers;

import eu.senla.naumovich.controllers.common.CRUDInterface;
import eu.senla.naumovich.dto.promotion.CreatePromotionAuthorDto;
import eu.senla.naumovich.dto.promotion.CreatePromotionGenreDto;
import eu.senla.naumovich.dto.promotion.PromotionDto;
import eu.senla.naumovich.dto.promotion.PromotionShortDto;
import eu.senla.naumovich.services.service.PromotionService;
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
@RequestMapping("/promotion")
public class PromotionController implements CRUDInterface<PromotionDto, PromotionShortDto> {

    private final PromotionService promotionService;

    @GetMapping
    public ResponseEntity<List<PromotionShortDto>> getAll(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sort", defaultValue = "percent") String sort) {
        log.info("An attempt to get promotions");
        List<PromotionShortDto> promotionDto = promotionService.getAll(page, size, sort);
        return ResponseEntity.ok(promotionDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PromotionDto> getById(@PathVariable("id") Long id) {
        log.info("An attempt to get promotion by id {}", id);
        PromotionDto promotionDto = promotionService.getById(id);
        return ResponseEntity.ok(promotionDto);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('UPDATE_PROMOTION')")
    public ResponseEntity<?> update(@Valid @RequestBody PromotionDto promotionDto) {
        log.info("An attempt to update promotion");
        PromotionDto promotion = promotionService.update(promotionDto);
        return new ResponseEntity<>(promotion, HttpStatus.CREATED);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_PROMOTION')")
    public ResponseEntity<?> create(@Valid @RequestBody PromotionDto promotionDto) {
        log.info("An attempt to create promotion");
        PromotionDto promotion = promotionService.create(promotionDto);
        return new ResponseEntity<>(promotion, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_PROMOTION')")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        log.info("An attempt to delete promotion by id {}", id);
        promotionService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/genre")
    @PreAuthorize("hasAuthority('CREATE_PROMOTION')")
    public ResponseEntity<?> createPromotionByGenre(@Valid @RequestBody CreatePromotionGenreDto createPromotion) {
        log.info("An attempt to create promotion by genre");
        PromotionDto promotionDto = promotionService.createPromotionByGenre(createPromotion);
        return new ResponseEntity<>(promotionDto, HttpStatus.CREATED);
    }

    @PostMapping("/author")
    @PreAuthorize("hasAuthority('CREATE_PROMOTION')")
    public ResponseEntity<?> createPromotionByAuthor(@Valid @RequestBody CreatePromotionAuthorDto createPromotion) {
        log.info("An attempt to create promotion by author name");
        PromotionDto promotionDto = promotionService.createPromotionByAuthor(createPromotion);
        return new ResponseEntity<>(promotionDto, HttpStatus.CREATED);
    }
}
