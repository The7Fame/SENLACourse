package eu.senla.naumovich.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import eu.senla.naumovich.dto.book.BookShortDto;
import eu.senla.naumovich.dto.order.OrderDto;
import eu.senla.naumovich.dto.order.OrderShortDto;
import eu.senla.naumovich.dto.order.view.View;
import eu.senla.naumovich.security.SecurityUser;
import eu.senla.naumovich.services.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @JsonView(View.WithPayment.class)
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<OrderShortDto>> getAll(@RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sort", defaultValue = "totalPrice") String sort) {
        log.info("An attempt to get orders");
        List<OrderShortDto> orderDto = orderService.getAll(page, size, sort);
        return ResponseEntity.ok(orderDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<OrderDto> getById(@PathVariable("id") Long id) {
        log.info("An attempt to get order by id {}", id);
        OrderDto orderDto = orderService.getById(id);
        return ResponseEntity.ok(orderDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        log.info("An attempt to delete order by id {}", id);
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @JsonView(View.WithoutPayment.class)
    @GetMapping("/my")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<OrderShortDto>> getUserOrders(@AuthenticationPrincipal SecurityUser securityUser,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        log.info("An attempt to get orders by user");
        List<OrderShortDto> orders = orderService.getUserOrders(securityUser, page, size);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/my/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<OrderDto> getUserOrder(@AuthenticationPrincipal SecurityUser securityUser,
            @PathVariable("id") Long id) {
        log.info("An attempt to get user order by id {}", id);
        OrderDto order = orderService.getUserOrderById(securityUser, id);
        return ResponseEntity.ok(order);
    }

    @PostMapping("/my")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> createOrder(@AuthenticationPrincipal SecurityUser securityUser,
            @Valid @RequestBody List<BookShortDto> books) {
        log.info("An attempt to create order by user");
        OrderDto orderDto = orderService.createOrderFromBooks(securityUser, books);
        return new ResponseEntity<>(orderDto, HttpStatus.CREATED);
    }

    @DeleteMapping("my/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> deleteUserOrder(@AuthenticationPrincipal SecurityUser securityUser,
            @PathVariable("id") Long id) {
        orderService.deleteUserOrderById(securityUser, id);
        return ResponseEntity.noContent().build();
    }

    @JsonView(View.WithoutPayment.class)
    @GetMapping("/filter")
    public ResponseEntity<List<OrderShortDto>> getGreaterThan(@RequestParam(name = "price") BigDecimal totalPrice){
        log.info("An attempt to get orders greater than {} price", totalPrice);
        List<OrderShortDto> orderDto = orderService.getGreaterThan(totalPrice);
        return ResponseEntity.ok(orderDto);
    }
}
