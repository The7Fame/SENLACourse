package eu.senla.naumovich.controllers;

import eu.senla.naumovich.controllers.common.CRUDInterface;
import eu.senla.naumovich.dto.OrderDto;
import eu.senla.naumovich.dto.UserDto;
import eu.senla.naumovich.security.SecurityUser;
import eu.senla.naumovich.services.service.CartService;
import eu.senla.naumovich.services.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController implements CRUDInterface<OrderDto> {
    private final OrderService orderService;
    private final CartService cartService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<OrderDto>> getAll(@RequestParam(name = "page", defaultValue = "1") int page,
                                                 @RequestParam(name = "size", defaultValue = "10") int size) {
        List<OrderDto> orderDto = orderService.getAll(page, size);
        return ResponseEntity.ok(orderDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<OrderDto> getById(@PathVariable("id") Long id) {
        OrderDto orderDto = orderService.getById(id);
        return ResponseEntity.ok(orderDto);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> update(@RequestBody OrderDto orderDto) {
        orderService.update(orderDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> create(@RequestBody OrderDto orderDto) {
        orderService.create(orderDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> createOrder(Authentication authentication) {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        System.out.println(cartService.booksInCart(securityUser.getId()));
       return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
