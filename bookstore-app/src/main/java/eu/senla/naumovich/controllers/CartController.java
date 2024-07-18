package eu.senla.naumovich.controllers;

import eu.senla.naumovich.dto.book.BookShortDto;
import eu.senla.naumovich.dto.book.BookToCartDto;
import eu.senla.naumovich.dto.cart.CartDto;
import eu.senla.naumovich.dto.cart.CartShortDto;
import eu.senla.naumovich.security.SecurityUser;
import eu.senla.naumovich.services.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<CartShortDto>> getAll(@RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sort", defaultValue = "id") String sort) {
        log.info("An attempt to get carts");
        List<CartShortDto> cartDto = cartService.getAll(page, size, sort);
        return ResponseEntity.ok(cartDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<BookShortDto>> getById(@PathVariable("id") Long id,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        log.info("An attempt to get user cart by id {}", id);
        List<BookShortDto> cartDto = cartService.getById(id, page, size);
        return ResponseEntity.ok(cartDto);
    }

    @PostMapping("/my")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> create(@Valid @AuthenticationPrincipal SecurityUser securityUser,
            @Valid @RequestBody BookToCartDto book) {
        log.info("An attempt to add item to cart");
        CartDto cart = cartService.addBookToCart(securityUser, book);
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }

    @DeleteMapping("/my/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        log.info("An attempt to delete item from cart");
        cartService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/my")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<BookShortDto>> getBooksInCart(@AuthenticationPrincipal SecurityUser securityUser,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        log.info("An attempt to get all items from the cart");
        List<BookShortDto> books = cartService.booksInCart(securityUser, page, size);
        return ResponseEntity.ok(books);
    }
}
