package eu.senla.naumovich.controllers;

import eu.senla.naumovich.controllers.common.CRUDInterface;
import eu.senla.naumovich.dto.promotion.CreatePromotionAuthorDto;
import eu.senla.naumovich.dto.promotion.CreatePromotionGenreDto;
import eu.senla.naumovich.dto.promotion.PromotionDto;
import eu.senla.naumovich.services.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/promotion")
public class PromotionController implements CRUDInterface<PromotionDto> {

    private final PromotionService promotionService;

    @GetMapping
    public ResponseEntity<List<PromotionDto>> getAll(@RequestParam(name = "page", defaultValue = "1") int page,
                                                     @RequestParam(name = "size", defaultValue = "10") int size) {
        List<PromotionDto> promotionDto = promotionService.getAll(page, size);
        return ResponseEntity.ok(promotionDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PromotionDto> getById(@PathVariable("id") Long id) {
        PromotionDto promotionDto = promotionService.getById(id);
        return ResponseEntity.ok(promotionDto);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('UPDATE_PROMOTION')")
    public ResponseEntity<?> update(@RequestBody PromotionDto promotionDto) {
        promotionService.update(promotionDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_PROMOTION')")
    public ResponseEntity<?> create(@RequestBody PromotionDto promotionDto) {
        promotionService.create(promotionDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_PROMOTION')")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        promotionService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/genre")
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<?> createPromotionByGenre(@RequestBody CreatePromotionGenreDto createPromotion){
        PromotionDto promotionDto = promotionService.createPromotionByGenre(createPromotion);
        return new ResponseEntity<>(promotionDto, HttpStatus.CREATED);
    }

    @PostMapping("/author")
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<?> createPromotionByAuthor(@RequestBody CreatePromotionAuthorDto createPromotion){
        PromotionDto promotionDto = promotionService.createPromotionByAuthor(createPromotion);
        return new ResponseEntity<>(promotionDto, HttpStatus.CREATED);
    }
}
