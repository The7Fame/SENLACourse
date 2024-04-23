package eu.senla.naumovich.controllers;

import eu.senla.naumovich.controllers.common.CRUDInterface;
import eu.senla.naumovich.dto.PromotionDto;
import eu.senla.naumovich.services.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/promotion")
public class PromotionController implements CRUDInterface<PromotionDto> {
    @Autowired
    private PromotionService promotionService;

    @GetMapping
    public ResponseEntity<List<PromotionDto>> getAll() {
        List<PromotionDto> promotionDto = promotionService.getAll();
        return ResponseEntity.ok(promotionDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PromotionDto> getById(@PathVariable("id") Long id) {
        PromotionDto promotionDto = promotionService.getById(id);
        if (promotionDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(promotionDto);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody PromotionDto promotionDto) {
        promotionService.update(promotionDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PromotionDto promotionDto) {
        promotionService.create(promotionDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        promotionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
