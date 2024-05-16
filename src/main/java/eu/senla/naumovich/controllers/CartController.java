package eu.senla.naumovich.controllers;

import eu.senla.naumovich.controllers.common.CRUDInterface;
import eu.senla.naumovich.dto.book.BookDto;
import eu.senla.naumovich.dto.cart.CartDto;
import eu.senla.naumovich.dto.user.UserDto;
import eu.senla.naumovich.security.SecurityUser;
import eu.senla.naumovich.services.service.CartService;
import eu.senla.naumovich.services.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<CartDto>> getAll(@RequestParam(name = "page", defaultValue = "1") int page,
                                                @RequestParam(name = "size", defaultValue = "10") int size) {
        List<CartDto> cartDto = cartService.getAll(page, size);
        return ResponseEntity.ok(cartDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CartDto> getById(@PathVariable("id") Long id) {
        CartDto cartDto = cartService.getById(id);
        return ResponseEntity.ok(cartDto);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> update(@RequestBody CartDto cartDto) {
        cartService.update(cartDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> create(@AuthenticationPrincipal SecurityUser securityUser, @RequestBody BookDto book) {
        CartDto cart = cartService.addBookToCart(securityUser, book);
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        cartService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/my")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<BookDto>> getBooksInCart(@AuthenticationPrincipal SecurityUser securityUser,
                                                        @RequestParam(name = "page", defaultValue = "1") int page,
                                                        @RequestParam(name = "size", defaultValue = "10") int size){
        List<BookDto> books = cartService.booksInCart(securityUser);
        return ResponseEntity.ok(books);
    }
}
