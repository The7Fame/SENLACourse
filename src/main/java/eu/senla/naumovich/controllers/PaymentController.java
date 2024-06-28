package eu.senla.naumovich.controllers;

import eu.senla.naumovich.dto.order.OrderCreateDto;
import eu.senla.naumovich.dto.payment.PaymentDto;
import eu.senla.naumovich.dto.payment.PaymentShortDto;
import eu.senla.naumovich.security.SecurityUser;
import eu.senla.naumovich.services.service.PaymentService;
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
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<PaymentShortDto>> getAll(@RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sort", defaultValue = "totalPrice") String sort) {
        log.info("An attempt to get payments");
        List<PaymentShortDto> paymentDto = paymentService.getAll(page, size, sort);
        return ResponseEntity.ok(paymentDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PaymentDto> getById(@PathVariable("id") Long id) {
        log.info("An attempt to get payment by id {}", id);
        PaymentDto paymentDto = paymentService.getById(id);
        return ResponseEntity.ok(paymentDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        log.info("An attempt to delete payment by id {}", id);
        paymentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/my")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<PaymentShortDto>> getUserPayments(@AuthenticationPrincipal SecurityUser securityUser,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        log.info("An attempt to get payments by user");
        List<PaymentShortDto> orders = paymentService.getUserPayments(securityUser, page, size);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/my/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<PaymentDto> getUserPayment(@AuthenticationPrincipal SecurityUser securityUser,
            @PathVariable("id") Long id) {
        log.info("An attempt to get user payment by id {}", id);
        PaymentDto payment = paymentService.getUserPaymentById(securityUser, id);
        return ResponseEntity.ok(payment);
    }

    @PostMapping("/my")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> createPayment(@AuthenticationPrincipal SecurityUser securityUser,
            @Valid @RequestBody OrderCreateDto orderCreateDto) {
        log.info("An attempt to create payment by user");
        PaymentDto paymentDto = paymentService.createPayment(securityUser, orderCreateDto);
        return new ResponseEntity<>(paymentDto, HttpStatus.CREATED);
    }
}
