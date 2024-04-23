package eu.senla.naumovich.controllers;

import eu.senla.naumovich.controllers.common.CRUDInterface;
import eu.senla.naumovich.dto.CartDto;
import eu.senla.naumovich.services.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController implements CRUDInterface<CartDto> {
    @Autowired
    private CartService cartService;

    @GetMapping
    public ResponseEntity<List<CartDto>> getAll() {
        List<CartDto> cartDto = cartService.getAll();
        return ResponseEntity.ok(cartDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartDto> getById(@PathVariable("id") Long id) {
        CartDto cartDto = cartService.getById(id);
        if (cartDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cartDto);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody CartDto cartDto) {
        cartService.update(cartDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CartDto cartDto) {
        cartService.create(cartDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        cartService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
