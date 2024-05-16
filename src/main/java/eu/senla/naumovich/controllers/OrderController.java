package eu.senla.naumovich.controllers;

import eu.senla.naumovich.controllers.common.CRUDInterface;
import eu.senla.naumovich.dto.book.BookDto;
import eu.senla.naumovich.dto.order.OrderDto;
import eu.senla.naumovich.security.SecurityUser;
import eu.senla.naumovich.services.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController implements CRUDInterface<OrderDto> {
    private final OrderService orderService;

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

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> createOrder(@AuthenticationPrincipal SecurityUser securityUser, @RequestBody List<BookDto> books) {
        OrderDto orderDto = orderService.createOrderFromBooks(securityUser, books);
        return new ResponseEntity<>(orderDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/my")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> getUserOrders(@AuthenticationPrincipal SecurityUser securityUser,
                                           @RequestParam(name = "page", defaultValue = "1") int page,
                                           @RequestParam(name = "size", defaultValue = "10") int size){
        List<OrderDto> orders = orderService.getUserOrders(securityUser, page, size);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/my/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> getUserOrder(@AuthenticationPrincipal SecurityUser securityUser,
                                          @PathVariable("id") int id){
        OrderDto order = orderService.getUserOrderById(securityUser, id);
        return ResponseEntity.ok(order);
    }
}
