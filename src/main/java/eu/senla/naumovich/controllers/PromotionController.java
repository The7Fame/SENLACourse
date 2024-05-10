package eu.senla.naumovich.controllers;

import eu.senla.naumovich.controllers.common.CRUDInterface;
import eu.senla.naumovich.dto.PromotionDto;
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
    @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<List<PromotionDto>> getAll(@RequestParam(name = "page", defaultValue = "1") int page,
                                                     @RequestParam(name = "size", defaultValue = "10") int size) {
        List<PromotionDto> promotionDto = promotionService.getAll(page, size);
        return ResponseEntity.ok(promotionDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('ADMIN')")
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
}
